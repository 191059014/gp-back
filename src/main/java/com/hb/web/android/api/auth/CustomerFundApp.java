package com.hb.web.android.api.auth;

import com.hb.web.api.IAgentService;
import com.hb.web.api.ICustomerFundDetailService;
import com.hb.web.api.ICustomerFundService;
import com.hb.web.api.IOfflinePayService;
import com.hb.web.constant.enumutil.FundTypeEnum;
import com.hb.web.constant.enumutil.OfflineCheckStatusEnum;
import com.hb.web.constant.enumutil.OfflinePayStatusEnum;
import com.hb.web.model.*;
import com.hb.web.vo.appvo.request.DepositRequestVO;
import com.hb.web.vo.appvo.request.RechargeRequestVO;
import com.hb.web.util.BigDecimalUtils;
import com.hb.web.util.DateUtils;
import com.hb.web.util.LogUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

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
    private IAgentService iAgentService;

    @Autowired
    private IOfflinePayService iOfflinePayService;

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
            addOrUpdate.setAccountTotalMoney(rechargeMoney);// 账户总金额
            addOrUpdate.setUsableMoney(rechargeMoney);// 可用金额
            addOrUpdate.setTotalRechargeMoney(rechargeMoney);// 累计充值
            addOrUpdate.setCreateTime(DateUtils.getCurrentDate());
            addOrUpdate.setCreateUserId(userCache.getUserId());
            LOGGER.info(LogUtils.appLog("即将新增的充值信息：{}"), addOrUpdate.toString());
            result = iCustomerFundService.addCustomerFund(addOrUpdate);
        } else {
            /**
             * 更新用户资金信息
             */
            addOrUpdate.setAccountTotalMoney(BigDecimalUtils.add(query.getAccountTotalMoney(), rechargeMoney));// 账户总金额
            addOrUpdate.setUsableMoney(BigDecimalUtils.add(query.getUsableMoney(), rechargeMoney));// 可用金额
            addOrUpdate.setTotalRechargeMoney(BigDecimalUtils.add(addOrUpdate.getTotalRechargeMoney(), rechargeMoney));// 累计充值
            LOGGER.info(LogUtils.appLog("即将更新的充值信息：{}"), addOrUpdate.toString());
            result = iCustomerFundService.updateByPrimaryKeySelective(addOrUpdate);
        }
        if (result > 0) {
            LOGGER.info(LogUtils.appLog("充值成功：{}"), addOrUpdate);
            // 插入流水
            CustomerFundDetailDO customerFundDetailDO = new CustomerFundDetailDO();
            customerFundDetailDO.setUserId(userCache.getUserId());
            customerFundDetailDO.setUserName(userCache.getUserName());
            customerFundDetailDO.setAgentId(agent.getAgentId());
            customerFundDetailDO.setAgentName(agent.getAgentName());
            customerFundDetailDO.setHappenMoney(rechargeMoney);// 发生金额
            customerFundDetailDO.setAfterHappenMoney(rechargeMoney);// 发生后金额
            customerFundDetailDO.setFundType(FundTypeEnum.RECHARGE.getValue());// 资金类型
            customerFundDetailDO.setRemark(FundTypeEnum.RECHARGE.getDesc());// 备注
            customerFundDetailDO.setCreateTime(DateUtils.getCurrentDate());
            customerFundDetailDO.setCreateUserId(userCache.getUserId());
            customerFundDetailDO.setUpdateTime(DateUtils.getCurrentDate());
            customerFundDetailDO.setUpdateUserId(userCache.getUserId());
            int addOneResult = iCustomerFundDetailService.addOne(customerFundDetailDO);
            if (addOneResult > 0) {
                LOGGER.info(LogUtils.appLog("添加充值流水成功：{}"), customerFundDetailDO);
            } else {
                LOGGER.info(LogUtils.appLog("添加充值流水失败：{}"), customerFundDetailDO);
                throw new Exception("添加充值流水失败");
            }
            return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
        } else {
            LOGGER.info(LogUtils.appLog("充值失败：{}"), addOrUpdate);
            throw new Exception("添加充值流水失败");
        }
    }

    @ApiOperation(value = "提现（暂时模拟，后面调用支付平台）")
    @PostMapping("/deposit")
    public AppResultModel deposit(@RequestBody DepositRequestVO depositRequestVO) {
        LOGGER.info(LogUtils.appLog("提现，入参：{}"), depositRequestVO);
        UserDO userCache = getUserCache();
        CustomerFundDO query = new CustomerFundDO(userCache.getUserId());
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        if (customerFund == null) {
            LOGGER.info(LogUtils.appLog("查询不到用户的资金信息"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NO_FUND_INFO);
        }
        /**
         * 生成一条线下审批任务
         */
        BigDecimal depositMoney = depositRequestVO.getDepositMoney();
        OfflinePayChekDO add = new OfflinePayChekDO(userCache.getUserId());
        add.setHappenMoney(depositMoney);
        add.setCheckStatus(OfflineCheckStatusEnum.AUDITING.getValue());
        add.setPayStatus(OfflinePayStatusEnum.NOT_PAY.getValue());
        iOfflinePayService.addOne(add);

        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

}
