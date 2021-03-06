package com.hb.web.android.api.auth;

import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.facade.entity.*;
import com.hb.facade.enumutil.*;
import com.hb.facade.vo.appvo.request.AppPages;
import com.hb.facade.vo.appvo.request.DepositRequestVO;
import com.hb.facade.vo.appvo.request.RechargeRequestVO;
import com.hb.facade.vo.appvo.response.Rank;
import com.hb.facade.vo.appvo.response.UserRankResponseVO;
import com.hb.facade.vo.appvo.response.UserFundSubTotalResponseVO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.*;
import com.hb.web.util.LogUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * ========== 用户资金信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.auth.CustomerFundApp.java, v1.0
 * @date 2019年06月18日 22时04分
 */
@Api(tags = "[APP]用户资金")
@RestController
@RequestMapping("app/auth/customerFund")
public class CustomerFundApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFundApp.class);

    @Autowired
    private ICustomerFundService iCustomerFundService;

    @Autowired
    private ICustomerFundDetailService iCustomerFundDetailService;

    @Autowired
    private IOfflinePayService iOfflinePayService;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "获取客户资金信息")
    @PostMapping("/getFundInfo")
    public AppResultModel<CustomerFundDO> getFundInfo() {
        UserDO userCache = getCurrentUserCache();
        CustomerFundDO query = new CustomerFundDO(userCache.getUserId());
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        if (customerFund == null) {
            customerFund = new CustomerFundDO(userCache.getUserId());
            customerFund.preHandler();
        }
        LOGGER.info(LogUtils.appLog("获取客户资金信息，返回结果：{}"), customerFund);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, customerFund);
    }

    @ApiOperation(value = "充值（暂时模拟，后面调用支付平台）")
    @PostMapping("/recharge")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel recharge(@RequestBody RechargeRequestVO rechargeRequestVO) throws Exception {
        LOGGER.info(LogUtils.appLog("充值，入参：{}"), String.valueOf(rechargeRequestVO));
        BigDecimal rechargeMoney = rechargeRequestVO.getRechargeMoney();
        String payPassword = rechargeRequestVO.getPayPassword();
        if (BigDecimal.ZERO.compareTo(rechargeMoney) >= 0 || StringUtils.isBlank(payPassword)) {
            AppResultModel<Object> appResultModel = AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
            LOGGER.info(LogUtils.appLog("充值，出参：{}"), appResultModel);
            return appResultModel;
        }
        UserDO userCache = getCurrentUserCache();
        if (!StringUtils.equals(userCache.getPayPassword(), EncryptUtils.encode(payPassword))) {
            AppResultModel<Object> appResultModel = AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PAY_PASSWORD);
            LOGGER.info(LogUtils.appLog("充值，出参：{}"), appResultModel);
            return appResultModel;
        }
        // 查询客户资金信息
        CustomerFundDO query = iCustomerFundService.findCustomerFund(new CustomerFundDO(userCache.getUserId()));
        CustomerFundDO addOrUpdate = new CustomerFundDO();
        addOrUpdate.setUserId(userCache.getUserId());
        addOrUpdate.setUserName(userCache.getUserName());
        // 更新时间，更新人
        addOrUpdate.setUpdateTime(DateUtils.getCurrentDate());
        addOrUpdate.setUpdateUserId(userCache.getUserId());
        // 查询代理商
        AgentDO agentDO = new AgentDO();
        agentDO.setMobile(userCache.getInviterMobile());
        AgentDO agent = iAgentService.findAgent(agentDO);
        if (agent == null) {
            LOGGER.warn("agent of user[{}] is null", userCache.getUserId());
            agent = new AgentDO();
        }
        if (query == null) {
            /**
             * 新增用户资金信息
             */
            addOrUpdate.setAgentId(agent.getAgentId());
            addOrUpdate.setAgentName(agent.getAgentName());
            addOrUpdate.setCreateTime(DateUtils.getCurrentDate());
            addOrUpdate.setCreateUserId(userCache.getUserId());
            addOrUpdate.setUnit(userCache.getUnit());
            LOGGER.info(LogUtils.appLog("即将新增的充值信息：{}"), addOrUpdate.toString());
            iCustomerFundService.addCustomerFund(addOrUpdate);
        }
        /**
         * 新增用户资金流水
         */
        CustomerFundDetailDO detailDO = new CustomerFundDetailDO();
        detailDO.setUserId(userCache.getUserId());
        detailDO.setUserName(userCache.getUserName());
        detailDO.setAgentId(agent.getAgentId());
        detailDO.setAgentName(agent.getAgentName());
        detailDO.setHappenMoney(rechargeMoney);
        detailDO.setAfterHappenMoney(rechargeMoney);
        detailDO.setFundType(FundTypeEnum.RECHARGE.getValue());
        detailDO.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        detailDO.setRemark(FundTypeEnum.RECHARGE.getDesc());
        detailDO.setUnit(userCache.getUnit());
        iCustomerFundDetailService.addOne(detailDO);
        /**
         * 新增线下支付审核信息
         */
        OfflinePayChekDO offlinePayChekDO = new OfflinePayChekDO(userCache.getUserId());
        offlinePayChekDO.setHappenMoney(rechargeMoney);
        offlinePayChekDO.setPayChannel(OfflinePayChannelEnum.ALIPAY.getValue());
        offlinePayChekDO.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        offlinePayChekDO.setPayStatus(OfflinePayStatusEnum.NOT_PAY.getValue());
        offlinePayChekDO.setFundType(FundTypeEnum.RECHARGE.getValue());
        offlinePayChekDO.setDetailId(detailDO.getDetailId());
        offlinePayChekDO.setUnit(userCache.getUnit());
        iOfflinePayService.addOne(offlinePayChekDO);
        alarmTools.alert("APP", "用户资金", "充值", "用户【" + userCache.getUserName() + "】申请充值【" + rechargeMoney + "元】，请及时处理！");
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);

    }

    @ApiOperation(value = "提现（暂时模拟，后面调用支付平台）")
    @PostMapping("/deposit")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel deposit(@RequestBody DepositRequestVO depositRequestVO) {
        LOGGER.info(LogUtils.appLog("提现，入参：{}"), depositRequestVO);
        BigDecimal depositMoney = depositRequestVO.getDepositMoney();
        String payPassword = depositRequestVO.getPayPassword();
        if (BigDecimal.ZERO.compareTo(depositMoney) >= 0 || StringUtils.isBlank(payPassword)) {
            AppResultModel<Object> appResultModel = AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
            LOGGER.info(LogUtils.appLog("提现，出参：{}"), appResultModel);
            return appResultModel;
        }
        UserDO userCache = getCurrentUserCache();
        if (!StringUtils.equals(userCache.getPayPassword(), EncryptUtils.encode(payPassword))) {
            AppResultModel<Object> appResultModel = AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PAY_PASSWORD);
            LOGGER.info(LogUtils.appLog("提现，出参：{}"), appResultModel);
            return appResultModel;
        }
        AgentDO agent = iAgentService.getAgentByInviterMobile(userCache.getInviterMobile());
        /**
         * 冻结资金
         */
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(new CustomerFundDO(userCache.getUserId()));
        if (customerFund == null) {
            LOGGER.info(LogUtils.appLog("客户资金查询为空"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_MONEY);
        }
        BigDecimal freezeMoney = customerFund.getFreezeMoney();
        BigDecimal usableMoney = customerFund.getUsableMoney();
        if (depositMoney.compareTo(usableMoney) > 0) {
            LOGGER.info(LogUtils.appLog("提现金额大于可用余额"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_USEABLE_MONEY);
        }
        freezeMoney = BigDecimalUtils.add(freezeMoney, depositMoney);
        usableMoney = BigDecimalUtils.subtract(usableMoney, depositMoney);

        customerFund.setFreezeMoney(freezeMoney);
        customerFund.setUsableMoney(usableMoney);
        // 累计提现
        customerFund.setTotalWithdrawMoney(BigDecimalUtils.add(customerFund.getTotalWithdrawMoney(), depositMoney));
        // 累计出入金额
        customerFund.setTotalInAndOutMoney(BigDecimalUtils.add(customerFund.getTotalInAndOutMoney(), depositMoney));
        iCustomerFundService.updateByPrimaryKeySelective(customerFund);
        /**
         * 新增用户资金流水
         */
        CustomerFundDetailDO detailDO = new CustomerFundDetailDO();
        detailDO.setUserId(userCache.getUserId());
        detailDO.setUserName(userCache.getUserName());
        detailDO.setAgentId(agent.getAgentId());
        detailDO.setAgentName(agent.getAgentName());
        detailDO.setHappenMoney(depositMoney);
        detailDO.setAfterHappenMoney(depositMoney);
        detailDO.setFundType(FundTypeEnum.DEPOSIT.getValue());
        detailDO.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        detailDO.setRemark(FundTypeEnum.DEPOSIT.getDesc());
        detailDO.setUnit(userCache.getUnit());
        iCustomerFundDetailService.addOne(detailDO);
        /**
         * 生成一条线下审批任务
         */
        OfflinePayChekDO add = new OfflinePayChekDO(userCache.getUserId());
        add.setHappenMoney(depositMoney);
        add.setPayChannel(OfflinePayChannelEnum.ALIPAY.getValue());
        add.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        add.setPayStatus(OfflinePayStatusEnum.NOT_PAY.getValue());
        add.setFundType(FundTypeEnum.DEPOSIT.getValue());
        add.setDetailId(detailDO.getDetailId());
        add.setUnit(userCache.getUnit());
        iOfflinePayService.addOne(add);
        alarmTools.alert("APP", "客户资金", "提现", "用户【" + userCache.getUserName() + "】申请提现【" + depositMoney + "元】，请及时处理！");
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    /**
     * ########## 查询客户资金分类汇总 ##########
     *
     * @return 客户资金分类汇总信息
     */
    @ApiOperation(value = "查询客户资金分类汇总信息（不要使用，后面会删除）")
    @PostMapping("/getUserFundSubTotal")
    @Deprecated
    public AppResultModel<UserFundSubTotalResponseVO> getUserFundSubTotal() {
        UserFundSubTotalResponseVO result = new UserFundSubTotalResponseVO();
        UserDO userCache = this.getCurrentUserCache();
        CustomerFundDO customerFund = this.iCustomerFundService.findCustomerFund(new CustomerFundDO(userCache.getUserId()));
        result.setAccountTotalMoney(customerFund.getAccountTotalMoney());
        result.setUsableMoney(customerFund.getUsableMoney());
        result.setTotalProfitAndLossMoney(customerFund.getTotalProfitAndLossMoney());
        Set<Integer> orderStatuSet = new HashSet<>();
        orderStatuSet.add(OrderStatusEnum.IN_THE_POSITION.getValue());
        Set<String> userIdSet = new HashSet<>();
        userIdSet.add(userCache.getUserId());
        List<OrderDO> orderList = this.iOrderService.findByUserIdSetAndOrderStatus(userIdSet, orderStatuSet);
        BigDecimal totalStrategyMoney = BigDecimal.ZERO;
        BigDecimal totalStrategyOwnMoney = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (OrderDO orderDO : orderList) {
                totalStrategyMoney = BigDecimalUtils.add(totalStrategyMoney, orderDO.getStrategyMoney());
                totalStrategyOwnMoney = BigDecimalUtils.add(totalStrategyOwnMoney, orderDO.getStrategyOwnMoney());
            }
        }
        result.setTotalStrategyMoney(totalStrategyMoney);
        result.setTotalStrategyOwnMoney(totalStrategyOwnMoney);
        LOGGER.info(LogUtils.appLog("查询客户资金分类汇总，出参：{}"), result);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, result);
    }

    /**
     * 查询排行榜
     *
     * @param appPages 分页参数
     * @return 排行信息
     */
    @ApiOperation(value = "查询排行榜")
    @PostMapping("/getRankList")
    public AppResultModel<UserRankResponseVO> getRankList(@RequestBody AppPages appPages) {
        LOGGER.info(LogUtils.appLog("查询排行榜，入参：{}"), appPages);
        List<CustomerFundDO> fundList = iCustomerFundService.getRankList(appPages.getStartRow(), appPages.getPageSize());
        if (CollectionUtils.isEmpty(fundList)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        Set<String> userIdSet = fundList.stream().map(CustomerFundDO::getUserId).collect(Collectors.toSet());
        Map<String, UserDO> userMap = iUserService.getUserMapByUserIdSet(userIdSet);
        UserRankResponseVO responseVO = new UserRankResponseVO();
        List<Rank> rankList = new ArrayList<>();
        for (CustomerFundDO customerFundDO : fundList) {
            BigDecimal totalProfitAndLossMoney = customerFundDO.getTotalProfitAndLossMoney();
            if (BigDecimal.ZERO.compareTo(totalProfitAndLossMoney) > 0) {
                continue;
            }
            Rank rank = new Rank();
            String userName = userMap.get(customerFundDO.getUserId()) == null ? null : userMap.get(customerFundDO.getUserId()).getUserName();
            rank.setUserName(userName);
            rank.setTotalProfitAndLossMoney(totalProfitAndLossMoney);
            rankList.add(rank);
        }
        responseVO.setRankList(rankList);
        LOGGER.info(LogUtils.appLog("查询排行榜，出参：{}"), responseVO);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}
