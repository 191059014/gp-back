package com.hb.web.android.api.auth;

import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.facade.entity.*;
import com.hb.facade.enumutil.*;
import com.hb.facade.vo.appvo.request.DepositRequestVO;
import com.hb.facade.vo.appvo.request.RechargeRequestVO;
import com.hb.facade.vo.appvo.response.UserFundSubTotalResponseVO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IAgentService;
import com.hb.web.api.ICustomerFundService;
import com.hb.web.api.IOfflinePayService;
import com.hb.web.api.IOrderService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private IOfflinePayService iOfflinePayService;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IAgentService iAgentService;

    @ApiOperation(value = "获取客户资金信息")
    @PostMapping("/getFundInfo")
    public AppResultModel<CustomerFundDO> getFundInfo() {
        UserDO userCache = getUserCache();
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
        String rechargeMoneyStr = rechargeRequestVO.getRechargeMoney();
        if (rechargeRequestVO == null || StringUtils.isBlank(rechargeMoneyStr)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        UserDO userCache = getUserCache();
        BigDecimal rechargeMoney = new BigDecimal(rechargeMoneyStr);
        // 查询客户资金信息
        CustomerFundDO query = iCustomerFundService.findCustomerFund(new CustomerFundDO(userCache.getUserId()));
        int result = 0;
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
        OfflinePayChekDO offlinePayChekDO = new OfflinePayChekDO(userCache.getUserId());
        offlinePayChekDO.setHappenMoney(rechargeMoney);
        offlinePayChekDO.setPayChannel(OfflinePayChannelEnum.ALIPAY.getValue());
        offlinePayChekDO.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        offlinePayChekDO.setPayStatus(OfflinePayStatusEnum.NOT_PAY.getValue());
        offlinePayChekDO.setFundType(FundTypeEnum.RECHARGE.getValue());
        iOfflinePayService.addOne(offlinePayChekDO);

        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);

    }

    @ApiOperation(value = "提现（暂时模拟，后面调用支付平台）")
    @PostMapping("/deposit")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AppResultModel deposit(@RequestBody DepositRequestVO depositRequestVO) {
        LOGGER.info(LogUtils.appLog("提现，入参：{}"), depositRequestVO);
        UserDO userCache = getUserCache();
        BigDecimal depositMoney = depositRequestVO.getDepositMoney();
        /**
         * 生成一条线下审批任务
         */
        OfflinePayChekDO add = new OfflinePayChekDO(userCache.getUserId());
        add.setHappenMoney(depositMoney);
        add.setPayChannel(OfflinePayChannelEnum.ALIPAY.getValue());
        add.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        add.setPayStatus(OfflinePayStatusEnum.NOT_PAY.getValue());
        add.setFundType(FundTypeEnum.DEPOSIT.getValue());
        iOfflinePayService.addOne(add);
        alarmTools.alert("APP", "客户资金", "提现", "用户【" + userCache.getUserName() + "】发起提现申请，金额【" + depositMoney + "】");
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    /**
     * ########## 查询客户资金分类汇总 ##########
     *
     * @return 客户资金分类汇总信息
     */
    @ApiOperation(value = "查询客户资金分类汇总信息")
    @PostMapping("/getUserFundSubTotal")
    public AppResultModel<UserFundSubTotalResponseVO> getUserFundSubTotal() {
        UserFundSubTotalResponseVO result = new UserFundSubTotalResponseVO();
        UserDO userCache = this.getUserCache();
        CustomerFundDO customerFund = this.iCustomerFundService.findCustomerFund(new CustomerFundDO(userCache.getUserId()));
        result.setAccountTotalMoney(customerFund.getAccountTotalMoney());
        result.setUsableMoney(customerFund.getUsableMoney());
        result.setTotalProfitAndLossMoney(customerFund.getTotalProfitAndLossMoney());
        Set<Integer> orderStatuSet = new HashSet<>();
        orderStatuSet.add(OrderStatusEnum.IN_THE_POSITION.getValue());
        orderStatuSet.add(OrderStatusEnum.DELEGATION.getValue());
        List<OrderDO> orderList = this.iOrderService.findByUserIdAndOrderStatus(userCache.getUserId(), orderStatuSet);
        BigDecimal totalStrategyMoney = BigDecimal.ZERO;
        BigDecimal totalStrategyOwnMoney = BigDecimal.ZERO;
        for (OrderDO orderDO : orderList) {
            totalStrategyMoney = BigDecimalUtils.add(totalStrategyMoney, orderDO.getStrategyMoney());
            totalStrategyOwnMoney = BigDecimalUtils.add(totalStrategyOwnMoney, orderDO.getStrategyOwnMoney());
        }
        result.setTotalStrategyMoney(totalStrategyMoney);
        result.setTotalStrategyOwnMoney(totalStrategyOwnMoney);
        LOGGER.info(LogUtils.appLog("查询客户资金分类汇总，出参：{}"), result);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, result);
    }

}