package com.hb.web.android.api.auth;

import com.hb.facade.calc.StockTools;
import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.facade.common.SystemConfig;
import com.hb.facade.entity.*;
import com.hb.facade.enumutil.FundTypeEnum;
import com.hb.facade.enumutil.OrderStatusEnum;
import com.hb.facade.vo.appvo.request.*;
import com.hb.facade.vo.appvo.response.OrderQueryResponseVO;
import com.hb.facade.vo.appvo.response.QueryOrderPageResponseVO;
import com.hb.remote.model.StockModel;
import com.hb.remote.service.IStockService;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.CloneUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.*;
import com.hb.web.mock.MockUtils;
import com.hb.web.util.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ========== 订单 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.auth.OrderApp.java, v1.0
 * @date 2019年06月26日 10时30分
 */
@Api(tags = "[APP]订单")
@RestController
@RequestMapping("app/auth/order")
public class OrderApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApp.class);

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private ICustomerFundService iCustomerFundService;

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private ICustomerFundDetailService iCustomerFundDetailService;

    @Autowired
    private IStockService iStockService;

    @Autowired
    private IStockListService iStockListService;

    @Value("${gpweb.delayDays.max}")
    private int delayDaysMax;

    @ApiOperation(value = "股票下单")
    @PostMapping("/order")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel order(@RequestBody OrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("股票下单，入参：{}"), requestVO);
//        if (!StockTools.stockOnLine()) {
//            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_TRADE_TIME);
//        }
        Set<String> upOrLowerStopStock = redisCacheManage.getUpOrLowerStopStockCache();
        if (upOrLowerStopStock.contains(requestVO.getStockCode())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.UP_OR_LOW_STOP);
        }
        UserDO userCache = getCurrentUserCache();
        String userId = userCache.getUserId();
        CustomerFundDO query = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        BigDecimal strategyOwnMoney = requestVO.getStrategyOwnMoney();
        BigDecimal strategyMoney = requestVO.getStrategyMoney();
        BigDecimal usableMoney = customerFund.getUsableMoney();
        BigDecimal serviceMoney = StockTools.calcServiceMoney(strategyMoney, SystemConfig.getAppJson().getServiceMoneyPercent());
        BigDecimal needMoney = BigDecimalUtils.add(strategyOwnMoney, serviceMoney);
        if (needMoney.compareTo(usableMoney) > 0) {
            // 余额不足
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_MONEY);
        }
        // 查询当前时间股票行情
        StockModel stockModel = null;
        if (false) {
            stockModel = MockUtils.mockStock();
        } else {
            StockListDO stock = iStockListService.findStock(new StockListDO(requestVO.getStockCode()));
            stockModel = iStockService.queryStock(stock.getFull_code());
        }
        LOGGER.info(LogUtils.appLog("买入，当前时间股票行情：{}"), stockModel);
        BigDecimal currentPrice = stockModel.getCurrentPrice();
        OrderDO insertOrder = CloneUtils.clone(requestVO, OrderDO.class);
        // 用户ID
        insertOrder.setUserId(userId);
        // 用户姓名
        insertOrder.setUserName(userCache.getUserName());
        // 订单状态
        insertOrder.setOrderStatus(OrderStatusEnum.IN_THE_POSITION.getValue());
        // 买入时间
        Date buyTime = new Date();
        insertOrder.setBuyTime(buyTime);
        // 买入价格
        insertOrder.setBuyPrice(stockModel.getCurrentPrice());
        // 买入总金额
        insertOrder.setBuyPriceTotal(BigDecimalUtils.multiply(new BigDecimal(requestVO.getBuyNumber()), currentPrice));
        // 服务费
        insertOrder.setServiceMoney(serviceMoney);
        // 递延天数
        insertOrder.setDelayDays(1);
        // 递延费
        insertOrder.setDelayMoney(BigDecimal.ZERO);
        // 已递延天数
        insertOrder.setAlreadyDelayDays(0);
        // 剩余递延天数
        insertOrder.setResidueDelayDays(1);
        // 递延到期时间
        insertOrder.setDelayEndTime(StockTools.calcSellDate(buyTime, 1));
        insertOrder.setUnit(userCache.getUnit());
        // 委托价格
        insertOrder.setEntrustPrice(requestVO.getBuyPrice());
        // 委托股数
        insertOrder.setEntrustNumber(requestVO.getBuyNumber());
        int i = iOrderService.insertSelective(insertOrder);
        if (i < 1) {
            LOGGER.info(LogUtils.appLog("股票下单失败"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        LOGGER.info(LogUtils.appLog("插入订单成功"));

        /**
         * 更新账户信息
         *
         * 1.总金额
         * 2.交易冻结金额增加
         * 3.可用余额减少
         * 4.累计信息服务费增加
         */
        CustomerFundDO updateCustomerFund = new CustomerFundDO(customerFund.getUserId());
        // 总金额=原总金额-服务费
        BigDecimal newAccountTotalMoney = BigDecimalUtils.subtract(customerFund.getAccountTotalMoney(), serviceMoney);
        updateCustomerFund.setAccountTotalMoney(newAccountTotalMoney);
        // 可用余额=原可用余额-策略本金-服务费
        BigDecimal newUsableMoney = BigDecimalUtils.subtractAll(BigDecimalUtils.DEFAULT_SCALE, customerFund.getUsableMoney(), strategyOwnMoney, serviceMoney);
        updateCustomerFund.setUsableMoney(newUsableMoney);
        // 交易冻结金额=原交易冻结金额+策略本金
        BigDecimal newTradeFreezeMoney = BigDecimalUtils.add(customerFund.getTradeFreezeMoney(), strategyOwnMoney);
        updateCustomerFund.setTradeFreezeMoney(newTradeFreezeMoney);
        // 累计服务费=原累计服务费+服务费
        BigDecimal newTotalMessageServiceMoney = BigDecimalUtils.add(customerFund.getTotalMessageServiceMoney(), serviceMoney);
        updateCustomerFund.setTotalMessageServiceMoney(newTotalMessageServiceMoney);
        // 累计持仓市值总金额
        updateCustomerFund.setTotalStrategyMoney(BigDecimalUtils.add(customerFund.getTotalStrategyMoney(), strategyMoney));
        // 累计持仓信用金总金额
        updateCustomerFund.setTotalStrategyOwnMoney(BigDecimalUtils.add(customerFund.getTotalStrategyOwnMoney(), strategyOwnMoney));
        // 更新时间
        updateCustomerFund.setUpdateTime(new Date());
        iCustomerFundService.updateByPrimaryKeySelective(updateCustomerFund);
        LOGGER.info(LogUtils.appLog("股票下单成功后更新账户信息成功"));

        /**
         * 生成服务费资金流水
         */
        AgentDO agent = iAgentService.getAgentByInviterMobile(userCache.getInviterMobile());
        CustomerFundDetailDO serviceAdd = new CustomerFundDetailDO();
        serviceAdd.setUserId(userId);
        serviceAdd.setUserName(userCache.getUserName());
        serviceAdd.setAgentId(agent.getAgentId());
        serviceAdd.setAgentName(agent.getAgentName());
        serviceAdd.setHappenMoney(serviceMoney);
        serviceAdd.setAfterHappenMoney(serviceMoney);
        serviceAdd.setFundType(FundTypeEnum.SERVICE.getValue());
        serviceAdd.setRemark(FundTypeEnum.SERVICE.getDesc());
        LOGGER.info(LogUtils.appLog("下单接口-新增服务费资金流水：{}"), serviceAdd);
        iCustomerFundDetailService.addOne(serviceAdd);

        alarmTools.alert("APP", "订单", "下单接口", "用户【" + userCache.getUserName() + "】下单成功，订单号：" + insertOrder.getOrderId());
        LOGGER.info(LogUtils.appLog("股票下单成功：{}"), insertOrder);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "根据订单状态分页查询订单")
    @PostMapping("/queryOrder")
    public AppResultModel<OrderQueryResponseVO> queryOrder(@RequestBody QueryOrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("根据订单状态分页查询订单，入参：{}"), requestVO);
        OrderQueryResponseVO responseVO = new OrderQueryResponseVO();
        UserDO userCache = getCurrentUserCache();
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderStatus(requestVO.getOrderStatus());
        orderDO.setUserId(userCache.getUserId());
        List<OrderDO> list = iOrderService.findListPages(orderDO, requestVO.getStartRow(), requestVO.getPageNum());
        Set<String> stockCodeSet = list.stream().map(OrderDO::getStockCode).collect(Collectors.toSet());
        Map<String, StockListDO> stockMap = iStockListService.getStockMapBySet(stockCodeSet);
        List<QueryOrderPageResponseVO> orderList = new ArrayList<>();
        list.forEach(o -> {
            QueryOrderPageResponseVO vo = CloneUtils.clone(o, QueryOrderPageResponseVO.class);
            Integer state = stockMap.get(o.getStockCode()) == null ? null : stockMap.get(o.getStockCode()).getState();
            vo.setStockState(state);
            orderList.add(vo);
        });
        responseVO.setOrderList(orderList);
        LOGGER.info(LogUtils.appLog("根据订单状态分页查询订单，返回结果：{}"), orderList);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

    @ApiOperation(value = "根据订单ID查询订单信息")
    @PostMapping("/queryOrderById")
    public AppResultModel<OrderDO> queryOrderById(@RequestBody OrderDO requestVO) {
        LOGGER.info(LogUtils.appLog("根据订单ID查询订单信息，入参：{}"), requestVO);
        if (StringUtils.isBlank(requestVO.getOrderId())) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        OrderDO orderDO = iOrderService.selectByPrimaryKey(requestVO.getOrderId());
        LOGGER.info(LogUtils.appLog("根据订单ID查询订单信息，返回结果：{}"), orderDO);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, orderDO);
    }

    @ApiOperation(value = "卖出")
    @PostMapping("/sellOrder")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel sellOrder(@RequestBody SellOrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("卖出，入参：{}"), requestVO);
//        if (!StockTools.stockOnLine()) {
//            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_TRADE_TIME);
//        }
        String orderId = requestVO.getOrderId();
        if (StringUtils.isBlank(orderId)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        // 查询当前时间股票行情
        StockModel stockModel = null;
        if (false) {
            stockModel = MockUtils.mockStock();
        } else {
            StockListDO stock = iStockListService.findStock(new StockListDO(orderDO.getStockCode()));
            stockModel = iStockService.queryStock(stock.getFull_code());
        }
        LOGGER.info(LogUtils.appLog("卖出，当前时间股票行情：{}"), stockModel);
        UserDO userCache = getCurrentUserCache();
        AgentDO agent = iAgentService.getAgentByInviterMobile(userCache.getInviterMobile());
        completeOrder(orderDO, stockModel, userCache, agent);
        /**
         * 增加已结算流水 TODO
         */
        alarmTools.alert("APP", "订单", "平仓接口", "用户【" + userCache.getUserName() + "】卖出订单，订单号：" + orderId + "，卖出价格：" + stockModel.getCurrentPrice());
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    /**
     * 平仓
     *
     * @param orderDO    订单信息
     * @param stockModel 股票信息
     * @param user       用户信息
     * @param agent      代理商信息
     */
    private void completeOrder(OrderDO orderDO, StockModel stockModel, UserDO user, AgentDO agent) {
        String userId = user.getUserId();
        String userName = user.getUserName();
        BigDecimal strategyMoney = orderDO.getStrategyMoney();
        BigDecimal strategyOwnMoney = orderDO.getStrategyOwnMoney();
        BigDecimal currentPrice = stockModel.getCurrentPrice();
        /**
         * 更新订单信息
         */
        BigDecimal profit = StockTools.calcOrderProfit(orderDO.getBuyPrice(), currentPrice, orderDO.getBuyNumber());
        // 卖出 价格
        orderDO.setSellPrice(currentPrice);
        // 卖出股数
        orderDO.setSellNumber(orderDO.getBuyNumber());
        // 卖出总价格
        orderDO.setSellPriceTotal(BigDecimalUtils.add(strategyMoney, profit));
        // 卖出时间
        orderDO.setSellTime(new Date());
        // 订单状态
        orderDO.setOrderStatus(OrderStatusEnum.ALREADY_SELL.getValue());
        // 利润
        orderDO.setProfit(profit);
        // 盈亏率
        orderDO.setProfitRate(StockTools.calcOrderProfitRate(profit, strategyMoney));
        int backDays = StockTools.calcBackDays(orderDO.getCreateTime(), orderDO.getDelayDays());
        // 计算退还递延天数和已递延天数
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        int nowDate = c1.get(Calendar.DATE);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(orderDO.getBuyTime());
        int buyDate = c2.get(Calendar.DATE);
        Calendar c3 = Calendar.getInstance();
        c3.setTime(orderDO.getDelayEndTime());
        int delayEndDate = c3.get(Calendar.DATE);
        if (nowDate == buyDate || nowDate == delayEndDate) {
            // 当前或者是卖出日期，退还为0，已递延为递延总天数-1-退换天数
            backDays = 0;
            orderDO.setAlreadyDelayDays(orderDO.getDelayDays() - 1);
        } else {
            orderDO.setAlreadyDelayDays(orderDO.getDelayDays() - 1 - backDays);
        }
        LOGGER.info(LogUtils.appLog("卖出，需要退还的递延金的天数：{}"), backDays);
        BigDecimal backDelayMoney = BigDecimal.ZERO;
        // 退换的递延天数
        orderDO.setBackDelayDays(backDays);
        if (backDays > 0) {
            // 退还递延金
            backDelayMoney = StockTools.calcDelayMoney(strategyMoney, backDays, SystemConfig.getAppJson().getDelayMoneyPercent());
            LOGGER.info(LogUtils.appLog("卖出，退还递延金：{}"), backDelayMoney);
            // 退还的递延金
            orderDO.setBackDelayMoney(backDelayMoney);
            // 递延金
            orderDO.setDelayMoney(BigDecimalUtils.subtract(orderDO.getDelayMoney(), backDelayMoney));
        }
        LOGGER.info(LogUtils.appLog("卖出-更新订单信息：{}"), orderDO);
        orderDO.setUpdateTime(new Date());
        iOrderService.updateByPrimaryKeySelective(orderDO);

        /**
         * 更新客户资金信息
         * 1.账户总金额变化
         * 2.可用余额变化
         * 3.冻结资金变化
         * 4.累计盈亏变化
         */
        CustomerFundDO query = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        // 账户总金额=原账户总金额+利润+退还的递延金
        BigDecimal add = BigDecimalUtils.addAll(BigDecimalUtils.DEFAULT_SCALE, customerFund.getAccountTotalMoney(), profit, backDelayMoney);
        customerFund.setAccountTotalMoney(add);
        // 可用余额=原可用余额+利润+退还的递延金+策略本金
        customerFund.setUsableMoney(BigDecimalUtils.addAll(BigDecimalUtils.DEFAULT_SCALE, customerFund.getUsableMoney(), strategyOwnMoney, profit, backDelayMoney));
        // 交易冻结金额=原交易冻结金额-策略本金
        customerFund.setTradeFreezeMoney(BigDecimalUtils.subtractAll(BigDecimalUtils.DEFAULT_SCALE, customerFund.getTradeFreezeMoney(), strategyOwnMoney));
        // 总盈亏=原总盈亏+利润
        customerFund.setTotalProfitAndLossMoney(BigDecimalUtils.add(customerFund.getTotalProfitAndLossMoney(), profit));
        // 累计持仓市值总金额=原累计持仓市值总金额-持仓市值
        customerFund.setTotalStrategyMoney(BigDecimalUtils.subtract(customerFund.getTotalStrategyMoney(), strategyMoney));
        // 累计持仓信用金总金额=原累计持仓信用金总金额-持仓信用金
        customerFund.setTotalStrategyOwnMoney(BigDecimalUtils.subtractAll(BigDecimalUtils.DEFAULT_SCALE, customerFund.getTotalStrategyOwnMoney(), strategyOwnMoney));
        customerFund.setUpdateTime(new Date());
        LOGGER.info(LogUtils.appLog("卖出-更新客户资金信息：{}"), customerFund);
        iCustomerFundService.updateByPrimaryKeySelective(customerFund);

        /**
         * 增加退还递延金流水
         */
        if (backDelayMoney.compareTo(BigDecimal.ZERO) > 0) {
            CustomerFundDetailDO backDelayDetail = new CustomerFundDetailDO();
            backDelayDetail.setUserId(userId);
            backDelayDetail.setUserName(userName);
            backDelayDetail.setAgentId(agent.getAgentId());
            backDelayDetail.setAgentName(agent.getAgentName());
            backDelayDetail.setHappenMoney(backDelayMoney);
            backDelayDetail.setAfterHappenMoney(backDelayMoney);
            backDelayDetail.setFundType(FundTypeEnum.DELAY_BACK.getValue());
            backDelayDetail.setRemark(FundTypeEnum.DELAY_BACK.getDesc());
            LOGGER.info(LogUtils.appLog("卖出-退还递延金流水：{}"), backDelayDetail);
            iCustomerFundDetailService.addOne(backDelayDetail);
        }
    }

    @ApiOperation(value = "递延")
    @PostMapping("/delay")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel delay(@RequestBody DelayRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("递延，入参：{}"), requestVO);
        UserDO userCache = getCurrentUserCache();
        String userId = userCache.getUserId();
        String orderId = requestVO.getOrderId();
        Integer delayDays = requestVO.getDelayDays();
        if (StringUtils.isBlank(orderId) || delayDays == null || delayDays.intValue() <= 0) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        int newDelayDays = Math.addExact(orderDO.getDelayDays(), delayDays);
        if (newDelayDays > delayDaysMax) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_UNKNOW.getCode(), "总递延天数超过最大限度：" + delayDaysMax + "天");
        }
        /**
         * 判断可以余额是否足够递延
         */
        CustomerFundDO customerFundUpdate = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(customerFundUpdate);
        BigDecimal delayMoney = StockTools.calcDelayMoney(orderDO.getStrategyMoney(), delayDays, SystemConfig.getAppJson().getDelayMoneyPercent());
        if (customerFund.getUsableMoney().compareTo(delayMoney) < 0) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_MONEY);
        }

        /**
         * 更新订单信息
         */
        OrderDO orderUpdate = new OrderDO(orderId, null);
        // 更新订单递延天数
        orderUpdate.setDelayDays(newDelayDays);
        // 更新订单剩余递延天数
        int newResidueDelayDays = Math.addExact(orderDO.getResidueDelayDays(), delayDays);
        orderUpdate.setResidueDelayDays(newResidueDelayDays);
        // 计算新的递延到期时间
        orderUpdate.setDelayEndTime(StockTools.calcSellDate(orderDO.getBuyTime(), newDelayDays));
        // 更新递延金
        orderUpdate.setDelayMoney(BigDecimalUtils.add(orderDO.getDelayMoney(), delayMoney));
        LOGGER.info(LogUtils.appLog("递延-更新订单信息：{}"), orderUpdate);
        orderDO.setUpdateTime(new Date());
        iOrderService.updateByPrimaryKeySelective(orderUpdate);

        /**
         * 更新账户信息
         * 1.总金额减少
         * 2.可用余额减少
         */
        customerFundUpdate.setUsableMoney(BigDecimalUtils.subtract(customerFund.getUsableMoney(), delayMoney));
        customerFundUpdate.setAccountTotalMoney(BigDecimalUtils.subtract(customerFund.getAccountTotalMoney(), delayMoney));
        LOGGER.info(LogUtils.appLog("递延-更新账户信息：{}"), customerFundUpdate);
        iCustomerFundService.updateByPrimaryKeySelective(customerFundUpdate);

        /**
         * 生成递延金流水
         */
        AgentDO agent = iAgentService.getAgentByInviterMobile(userCache.getInviterMobile());
        CustomerFundDetailDO customerFundDetailAdd = new CustomerFundDetailDO();
        customerFundDetailAdd.setUserId(userId);
        customerFundDetailAdd.setUserName(userCache.getUserName());
        customerFundDetailAdd.setAgentId(agent.getAgentId());
        customerFundDetailAdd.setAgentName(agent.getAgentName());
        customerFundDetailAdd.setHappenMoney(delayMoney);
        customerFundDetailAdd.setAfterHappenMoney(delayMoney);
        customerFundDetailAdd.setFundType(FundTypeEnum.DELAY.getValue());
        customerFundDetailAdd.setRemark(FundTypeEnum.DELAY.getDesc());
        LOGGER.info(LogUtils.appLog("递延-新增资金流水：{}"), customerFundDetailAdd);
        iCustomerFundDetailService.addOne(customerFundDetailAdd);

        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "追加信用金")
    @PostMapping("/appendOrderMoney")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel appendOrderMoney(@RequestBody AppendOrderMoneyRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("追加信用金，入参：{}"), requestVO);
        UserDO userCache = getCurrentUserCache();
        String userId = userCache.getUserId();
        BigDecimal appendMoney = requestVO.getAppendMoney();
        String orderId = requestVO.getOrderId();
        if (StringUtils.isBlank(orderId) || BigDecimal.ZERO.compareTo(appendMoney) == 0) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }

        /**
         * 更新客户资金信息
         * 1.可用余额减少
         * 2.订单冻结金额增加
         */
        CustomerFundDO customerFundUpdate = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(customerFundUpdate);
        customerFundUpdate.setUsableMoney(BigDecimalUtils.subtract(customerFund.getUsableMoney(), appendMoney));
        customerFundUpdate.setTradeFreezeMoney(BigDecimalUtils.add(customerFund.getTradeFreezeMoney(), appendMoney));
        // 累计持仓信用金=原累计持仓信用金+追加的信用金
        customerFundUpdate.setTotalStrategyOwnMoney(BigDecimalUtils.add(customerFund.getTotalStrategyOwnMoney(), appendMoney));
        LOGGER.info(LogUtils.appLog("追加信用金-更新客户资金信息：{}"), customerFundUpdate);
        iCustomerFundService.updateByPrimaryKeySelective(customerFundUpdate);

        /**
         * 更新订单信息
         * 1.追加信用金增加
         */
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        OrderDO orderUpdate = new OrderDO(orderId, null);
        orderUpdate.setStrategyOwnMoney(BigDecimalUtils.add(orderDO.getStrategyOwnMoney(), appendMoney));
        BigDecimal stopLossMoney = requestVO.getStopLossMoney();
        if (stopLossMoney != null && BigDecimal.ZERO.compareTo(stopLossMoney) != 0) {
            orderUpdate.setStopLossMoney(stopLossMoney);
        }
        LOGGER.info(LogUtils.appLog("追加信用金-更新订单信息：{}"), orderUpdate);
        orderDO.setUpdateTime(new Date());
        iOrderService.updateByPrimaryKeySelective(orderUpdate);

        /**
         * 新增资金流水
         */
        AgentDO agent = iAgentService.getAgentByInviterMobile(userCache.getInviterMobile());
        CustomerFundDetailDO customerFundDetailAdd = new CustomerFundDetailDO();
        customerFundDetailAdd.setUserId(userId);
        customerFundDetailAdd.setUserName(userCache.getUserName());
        customerFundDetailAdd.setAgentId(agent.getAgentId());
        customerFundDetailAdd.setAgentName(agent.getAgentName());
        customerFundDetailAdd.setHappenMoney(appendMoney);
        customerFundDetailAdd.setAfterHappenMoney(appendMoney);
        customerFundDetailAdd.setFundType(FundTypeEnum.APPEND.getValue());
        customerFundDetailAdd.setRemark(FundTypeEnum.APPEND.getDesc());
        LOGGER.info(LogUtils.appLog("追加信用金-新增资金流水：{}"), customerFundDetailAdd);
        iCustomerFundDetailService.addOne(customerFundDetailAdd);

        alarmTools.alert("APP", "订单", "追加信用金", "用户【" + userCache.getUserName() + "】追加信用金成功，订单号：" + orderId);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "放弃订单")
    @PostMapping("/cancelOrder")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel cancelOrder(@RequestBody SellOrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("放弃订单，入参：{}"), requestVO);
        if (!StockTools.stockOnLine()) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_TRADE_TIME);
        }
        String orderId = requestVO.getOrderId();
        if (StringUtils.isBlank(orderId)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getCurrentUserCache();
        String userId = userCache.getUserId();
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        BigDecimal strategyMoney = orderDO.getStrategyMoney();
        BigDecimal strategyOwnMoney = orderDO.getStrategyOwnMoney();
        // 查询当前时间股票行情
        StockListDO stock = iStockListService.findStock(new StockListDO(orderDO.getStockCode()));
        StockModel stockModel = iStockService.queryStock(stock.getFull_code());
        LOGGER.info(LogUtils.appLog("放弃订单，当前时间股票行情：{}"), stockModel);
        BigDecimal currentPrice = stockModel.getCurrentPrice();
        /**
         * 更新订单信息
         */
        BigDecimal profit = StockTools.calcOrderProfit(orderDO.getBuyPrice(), currentPrice, orderDO.getBuyNumber());
        // 卖出 价格
        orderDO.setSellPrice(currentPrice);
        // 卖出总价格
        orderDO.setSellPriceTotal(BigDecimalUtils.add(strategyMoney, profit));
        // 订单状态
        orderDO.setOrderStatus(OrderStatusEnum.GIVEUP.getValue());
        // 利润
        orderDO.setProfit(profit);
        // 盈亏率
        orderDO.setProfitRate(StockTools.calcOrderProfitRate(profit, strategyMoney));
        // 卖出时间
        orderDO.setSellTime(new Date());
        orderDO.setCancelTime(new Date());
        orderDO.setUpdateTime(new Date());
        int backDays = StockTools.calcBackDays(orderDO.getCreateTime(), orderDO.getDelayDays());
        LOGGER.info(LogUtils.appLog("放弃订单，需要退还的递延金的天数：{}"), backDays);
        BigDecimal backDelayMoney = BigDecimal.ZERO;
        // 退换的递延天数
        orderDO.setBackDelayDays(backDays);
        if (backDays > 0) {
            // 退还递延金
            backDelayMoney = StockTools.calcDelayMoney(strategyMoney, backDays, SystemConfig.getAppJson().getDelayMoneyPercent());
            LOGGER.info(LogUtils.appLog("放弃订单，退还递延金：{}"), backDelayMoney);
            // 退还的递延金
            orderDO.setBackDelayMoney(backDelayMoney);
            // 递延金
            orderDO.setDelayMoney(BigDecimalUtils.subtract(orderDO.getDelayMoney(), backDelayMoney));
        }
        LOGGER.info(LogUtils.appLog("放弃订单-更新订单信息：{}"), orderDO);
        iOrderService.updateByPrimaryKeySelective(orderDO);

        /**
         * 更新客户资金信息
         * 1.账户总金额变化
         * 2.可用余额变化
         * 3.冻结资金变化
         * 4.累计盈亏变化
         */
        CustomerFundDO query = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        customerFund.setAccountTotalMoney(BigDecimalUtils.add(customerFund.getAccountTotalMoney(), profit));
        customerFund.setUsableMoney(BigDecimalUtils.addAll(BigDecimalUtils.DEFAULT_SCALE, customerFund.getUsableMoney(), strategyOwnMoney, profit, backDelayMoney));
        customerFund.setTradeFreezeMoney(BigDecimalUtils.multiply(customerFund.getTradeFreezeMoney(), strategyMoney));
        customerFund.setTotalProfitAndLossMoney(BigDecimalUtils.add(customerFund.getTotalProfitAndLossMoney(), profit));
        // 累计持仓市值总金额
        customerFund.setTotalStrategyMoney(BigDecimalUtils.subtract(customerFund.getTotalStrategyMoney(), strategyMoney));
        // 累计持仓信用金总金额
        customerFund.setTotalStrategyOwnMoney(BigDecimalUtils.subtract(customerFund.getTotalStrategyOwnMoney(), strategyOwnMoney));
        LOGGER.info(LogUtils.appLog("放弃订单-更新客户资金信息：{}"), customerFund);
        iCustomerFundService.updateByPrimaryKeySelective(customerFund);

        /**
         * 增加退还递延金流水
         */
        AgentDO agent = iAgentService.getAgentByInviterMobile(userCache.getInviterMobile());
        if (backDelayMoney.compareTo(BigDecimal.ZERO) > 0) {
            CustomerFundDetailDO backDelayDetail = new CustomerFundDetailDO();
            backDelayDetail.setUserId(userId);
            backDelayDetail.setUserName(userCache.getUserName());
            backDelayDetail.setAgentId(agent.getAgentId());
            backDelayDetail.setAgentName(agent.getAgentName());
            backDelayDetail.setHappenMoney(backDelayMoney);
            backDelayDetail.setAfterHappenMoney(backDelayMoney);
            backDelayDetail.setFundType(FundTypeEnum.DELAY_BACK.getValue());
            backDelayDetail.setRemark(FundTypeEnum.DELAY_BACK.getDesc());
            LOGGER.info(LogUtils.appLog("放弃订单-退还递延金流水：{}"), backDelayDetail);
            iCustomerFundDetailService.addOne(backDelayDetail);
        }

        /**
         * 增加已结算流水 TODO
         */

        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

}
