package com.hb.web.android.api.auth;

import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.facade.entity.*;
import com.hb.facade.enumutil.FundTypeEnum;
import com.hb.facade.enumutil.OrderStatusEnum;
import com.hb.facade.vo.appvo.request.*;
import com.hb.facade.vo.appvo.response.OrderQueryResponseVO;
import com.hb.remote.model.StockModel;
import com.hb.remote.service.IStockService;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.CloneUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IAgentService;
import com.hb.web.api.ICustomerFundDetailService;
import com.hb.web.api.ICustomerFundService;
import com.hb.web.api.IOrderService;
import com.hb.web.tool.CalcTools;
import com.hb.web.tool.CheckTools;
import com.hb.web.util.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ApiOperation(value = "股票下单")
    @PostMapping("/order")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel order(@RequestBody OrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("股票下单，入参：{}"), requestVO);
        if (!CheckTools.stockOnLine()) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_TRADE_TIME);
        }
        UserDO userCache = getUserCache();
        String userId = userCache.getUserId();
        CustomerFundDO query = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        BigDecimal strategyOwnMoney = requestVO.getStrategyOwnMoney();
        BigDecimal usableMoney = customerFund.getUsableMoney();
        if (strategyOwnMoney.compareTo(usableMoney) > 0) {
            // 余额不足
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_MONEY);
        }
        OrderDO clone = CloneUtils.clone(requestVO, OrderDO.class);
        clone.setUserId(userId);
        clone.setUserName(userCache.getUserName());
        clone.setOrderStatus(OrderStatusEnum.IN_THE_POSITION.getValue());
        // 服务费
        BigDecimal strategyMoney = requestVO.getStrategyMoney();
        BigDecimal serviceMoney = CalcTools.calcServiceMoney(strategyMoney);
        clone.setServiceMoney(serviceMoney);
        // 递延金
        BigDecimal delayMoney = CalcTools.calcDelayMoney(strategyMoney, 1);
        clone.setDelayMoney(delayMoney);
        clone.setUnit(userCache.getUnit());
        int i = iOrderService.insertSelective(clone);
        if (i < 1) {
            LOGGER.info(LogUtils.appLog("股票下单失败"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        LOGGER.info(LogUtils.appLog("股票下单成功"));

        /**
         * 更新账户信息
         *
         * 1.总金额不变
         * 2.交易冻结金额增加
         * 3.可用余额减少
         * 4.累计信息服务费增加
         */
        CustomerFundDO updateCustomerFund = new CustomerFundDO(customerFund.getUserId());
        BigDecimal newTradeFreezeMoney = BigDecimalUtils.add(customerFund.getTradeFreezeMoney(), strategyOwnMoney);
        BigDecimal newUsableMoney = BigDecimalUtils.subtract(customerFund.getUsableMoney(), strategyOwnMoney);
        BigDecimal newTotalMessageServiceMoney = BigDecimalUtils.add(customerFund.getTotalMessageServiceMoney(), serviceMoney);
        updateCustomerFund.setTradeFreezeMoney(newTradeFreezeMoney);
        updateCustomerFund.setUsableMoney(newUsableMoney);
        updateCustomerFund.setTotalMessageServiceMoney(newTotalMessageServiceMoney);
        iCustomerFundService.updateByPrimaryKeySelective(updateCustomerFund);
        LOGGER.info(LogUtils.appLog("股票下单成功后更新账户信息成功"));

        /**
         * 生成资金流水
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

        CustomerFundDetailDO delayAdd = new CustomerFundDetailDO();
        delayAdd.setUserId(userId);
        delayAdd.setUserName(userCache.getUserName());
        delayAdd.setAgentId(agent.getAgentId());
        delayAdd.setAgentName(agent.getAgentName());
        delayAdd.setHappenMoney(delayMoney);
        delayAdd.setAfterHappenMoney(delayMoney);
        delayAdd.setFundType(FundTypeEnum.DELAY.getValue());
        delayAdd.setRemark(FundTypeEnum.DELAY.getDesc());
        LOGGER.info(LogUtils.appLog("下单接口-新增递延费资金流水：{}"), delayAdd);
        iCustomerFundDetailService.addOne(delayAdd);

        alarmTools.alert("APP", "订单", "下单接口", "用户【" + userCache.getUserName() + "】下单成功，订单号：" + clone.getOrderId());
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "查询订单")
    @PostMapping("/queryOrder")
    public AppResultModel<OrderQueryResponseVO> queryOrder(@RequestBody QueryOrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("查询订单，入参：{}"), requestVO);
        OrderQueryResponseVO responseVO = new OrderQueryResponseVO();
        UserDO userCache = getUserCache();
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderStatus(requestVO.getOrderStatus());
        orderDO.setUserId(userCache.getUserId());
        List<OrderDO> list = iOrderService.findListPages(orderDO, requestVO.getStartRow(), requestVO.getPageNum());
        responseVO.setOrderList(list);
        LOGGER.info(LogUtils.appLog("查询订单，返回结果：{}"), list);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

    @ApiOperation(value = "卖出")
    @PostMapping("/sellOrder")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel sellOrder(@RequestBody SellOrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("卖出，入参：{}"), requestVO);
        String orderId = requestVO.getOrderId();
        if (StringUtils.isBlank(orderId)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getUserCache();
        String userId = userCache.getUserId();
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        BigDecimal strategyMoney = orderDO.getStrategyMoney();
        BigDecimal strategyOwnMoney = orderDO.getStrategyOwnMoney();
        // 查询当前时间股票行情
        StockModel stockModel = iStockService.queryStock(orderDO.getStockCode());
        LOGGER.info(LogUtils.appLog("卖出，当前时间股票行情：{}"), stockModel);
        BigDecimal currentPrice = stockModel.getCurrentPrice();
        /**
         * 更新订单信息
         */
        BigDecimal profit = CalcTools.calcOrderProfit(orderDO.getBuyPrice(), currentPrice, orderDO.getBuyNumber());
        // 卖出 价格
        orderDO.setSellPrice(currentPrice);
        // 卖出总价格
        orderDO.setSellPriceTotal(BigDecimalUtils.add(strategyMoney, profit));
        // 订单状态
        orderDO.setOrderStatus(OrderStatusEnum.ALREADY_SETTLED.getValue());
        // 利润
        orderDO.setProfit(profit);
        // 盈亏率
        orderDO.setProfitRate(CalcTools.calcOrderProfitRate(profit, strategyMoney));

        int daysBetween = DateUtils.getDaysBetween(new Date(), orderDO.getCreateTime());
        LOGGER.info(LogUtils.appLog("卖出，从下单时间到卖出时间总天数：{}"), daysBetween);
        int backDays = Math.subtractExact(daysBetween, orderDO.getDelayDays());
        BigDecimal backDelayMoney = BigDecimal.ZERO;
        if (backDays > 0) {
            // 退还递延金
            backDelayMoney = CalcTools.calcDelayMoney(strategyMoney, backDays);
            LOGGER.info(LogUtils.appLog("卖出，退还递延金：{}"), backDelayMoney);
            // 递延金
            orderDO.setDelayMoney(BigDecimalUtils.subtract(orderDO.getDelayMoney(), backDelayMoney));
        }
        LOGGER.info(LogUtils.appLog("卖出-更新订单信息：{}"), orderDO);
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
        LOGGER.info(LogUtils.appLog("卖出-更新客户资金信息：{}"), customerFund);
        iCustomerFundService.addCustomerFund(customerFund);

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
            LOGGER.info(LogUtils.appLog("卖出-退还递延金流水：{}"), backDelayDetail);
            iCustomerFundDetailService.addOne(backDelayDetail);
        }

        /**
         * 增加已结算流水 TODO
         */

        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "递延")
    @PostMapping("/delay")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel delay(@RequestBody DelayRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("递延，入参：{}"), requestVO);
        UserDO userCache = getUserCache();
        String userId = userCache.getUserId();
        String orderId = requestVO.getOrderId();
        Integer delayDays = requestVO.getDelayDays();
        if (StringUtils.isBlank(orderId) || delayDays == null || delayDays.intValue() <= 0) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        /**
         * 判断可以余额是否足够递延
         */
        CustomerFundDO customerFundUpdate = new CustomerFundDO(userId);
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(customerFundUpdate);
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        BigDecimal delayMoney = CalcTools.calcDelayMoney(orderDO.getStrategyMoney(), delayDays);
        if (customerFund.getUsableMoney().compareTo(delayMoney) < 0) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_MONEY);
        }

        /**
         * 更新订单信息
         * 1.更新订单递延天数
         */
        OrderDO orderUpdate = new OrderDO(orderId, null);
        orderUpdate.setDelayDays(Math.addExact(orderDO.getDelayDays(), delayDays));
        orderUpdate.setDelayMoney(BigDecimalUtils.add(orderDO.getDelayMoney(), delayMoney));
        LOGGER.info(LogUtils.appLog("递延-更新订单信息：{}"), orderUpdate);
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
        UserDO userCache = getUserCache();
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
        LOGGER.info(LogUtils.appLog("追加信用金-更新客户资金信息：{}"), customerFundUpdate);
        iCustomerFundService.updateByPrimaryKeySelective(customerFundUpdate);

        /**
         * 更新订单信息
         * 1.追加信用金增加
         */
        OrderDO orderDO = iOrderService.selectByPrimaryKey(orderId);
        OrderDO orderUpdate = new OrderDO(orderId, null);
        orderUpdate.setAppendMoney(BigDecimalUtils.add(orderDO.getAppendMoney(), appendMoney));
        LOGGER.info(LogUtils.appLog("追加信用金-更新订单信息：{}"), orderUpdate);
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

}
