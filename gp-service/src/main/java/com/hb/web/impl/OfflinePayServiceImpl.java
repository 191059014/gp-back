package com.hb.web.impl;

import com.hb.facade.common.ResponseEnum;
import com.hb.facade.entity.*;
import com.hb.facade.enumutil.FundTypeEnum;
import com.hb.facade.enumutil.OfflineCheckStatusEnum;
import com.hb.facade.enumutil.OfflinePayChannelEnum;
import com.hb.facade.enumutil.OfflinePayStatusEnum;
import com.hb.remote.tool.AlarmTools;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.PageHelper;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.api.*;
import com.hb.web.exception.BusinessException;
import com.hb.web.mapper.OfflinePayCheckMapper;
import com.hb.web.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== 线下支付 ==========
 *
 * @author Mr.huang
 * @version OfflinePayServiceImpl.java, v1.0
 * @date 2019年06月12日 15时30分
 */
@Service
public class OfflinePayServiceImpl implements IOfflinePayService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OfflinePayServiceImpl.class);

    @Autowired
    private ICustomerFundService iCustomerFundService;

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICustomerFundDetailService iCustomerFundDetailService;

    @Autowired
    private OfflinePayCheckMapper offlinePayCheckMapper;

    @Autowired
    private AlarmTools alarmTools;

    @Override
    public List<Map<String, Object>> getOfflineCheckStatusList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OfflineCheckStatusEnum offlineCheckStatusEnum : OfflineCheckStatusEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", offlineCheckStatusEnum.getValue());
            map.put("name", offlineCheckStatusEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getOfflinePayStatusCombobox() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OfflinePayStatusEnum offlinePayStatusEnum : OfflinePayStatusEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", offlinePayStatusEnum.getValue());
            map.put("name", offlinePayStatusEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getOfflinePayChannelList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OfflinePayChannelEnum offlinePayChannelEnum : OfflinePayChannelEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", offlinePayChannelEnum.getValue());
            map.put("name", offlinePayChannelEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<OfflinePayChekDO> findList(OfflinePayChekDO offlinePayChekDO, Integer pageNum, Integer pageSize) {
        return offlinePayCheckMapper.findList(offlinePayChekDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public List<OfflinePayChekDO> findAppList(OfflinePayChekDO offlinePayChekDO, Integer startRow, Integer pageSize) {
        return offlinePayCheckMapper.findList(offlinePayChekDO, startRow, pageSize);
    }

    @Override
    public Integer findCount(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.findCount(offlinePayChekDO);
    }

    @Override
    public int addOne(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.insertSelective(offlinePayChekDO);
    }

    @Override
    public OfflinePayChekDO findOne(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.findOne(offlinePayChekDO);
    }

    @Override
    public void update(OfflinePayChekDO offlinePayChekDO) {
        if (FundTypeEnum.RECHARGE.getValue().equals(offlinePayChekDO.getFundType())) {
            // 充值
            rechargeMoney(offlinePayChekDO);
        } else if (FundTypeEnum.DEPOSIT.getValue().equals(offlinePayChekDO.getFundType())) {
            // 提现
            depositMoney(offlinePayChekDO);
        }
        // 更新线下支付信息
        updateByPrimaryKeySelective(offlinePayChekDO);
    }

    @Override
    public int updateByPrimaryKeySelective(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.updateByPrimaryKeySelective(offlinePayChekDO);
    }

    /**
     * ########## 提现 ##########
     *
     * @param offlinePayChekDO 提现信息
     */
    private void depositMoney(OfflinePayChekDO offlinePayChekDO) {
        if (!OfflineCheckStatusEnum.PASS.getValue().equals(offlinePayChekDO.getCheckStatus())) {
            return;
        }
        String userId = offlinePayChekDO.getUserId();
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(new CustomerFundDO(userId));
        LOGGER.info("查询用户的资金信息：{}", customerFund);
        if (customerFund == null) {
            throw new BusinessException(ResponseEnum.NO_FUND_INFO);
        }
        BigDecimal depositMoney = offlinePayChekDO.getHappenMoney();
        BigDecimal usableMoney = customerFund.getUsableMoney();
        if (depositMoney.compareTo(usableMoney) > 0) {
            LOGGER.info(LogUtils.appLog("提现金额大于可用余额"));
            throw new BusinessException(ResponseEnum.NOT_ENOUGH_USEABLE_MONEY);
        }
        /**
         * 更新用户资金信息
         * 可用余额减少
         * 冻结资金增加
         */
        BigDecimal newUseableMoney = BigDecimalUtils.subtract(usableMoney, depositMoney);
        customerFund.setUsableMoney(newUseableMoney);
        BigDecimal freezeMoney = customerFund.getFreezeMoney();
        BigDecimal newFreezeMoney = BigDecimalUtils.add(freezeMoney, depositMoney);
        customerFund.setFreezeMoney(newFreezeMoney);
        int result = iCustomerFundService.updateByPrimaryKeySelective(customerFund);
        LOGGER.info("更新用户资金信息：{}，结果：{}", customerFund, result);
        if (result <= 0) {
            throw new BusinessException(ResponseEnum.ADD_CUSTOMER_FUND_FAILED);
        }
        /**
         * 插入流水
         */
        UserDO user = iUserService.findUser(new UserDO(userId));
        AgentDO query = new AgentDO();
        query.setMobile(user.getInviterMobile());
        AgentDO agent = iAgentService.findAgent(query);

        CustomerFundDetailDO customerFundDetailDO = new CustomerFundDetailDO();
        customerFundDetailDO.setUserId(userId);
        customerFundDetailDO.setUserName(user.getUserName());
        customerFundDetailDO.setAgentId(agent.getAgentId());
        customerFundDetailDO.setAgentName(agent.getAgentName());
        customerFundDetailDO.setHappenMoney(depositMoney);// 发生金额
        customerFundDetailDO.setAfterHappenMoney(depositMoney);// 发生后金额
        customerFundDetailDO.setFundType(FundTypeEnum.DEPOSIT.getValue());// 资金类型
        customerFundDetailDO.setRemark(FundTypeEnum.DEPOSIT.getDesc());// 备注
        customerFundDetailDO.setCreateTime(DateUtils.getCurrentDate());
        customerFundDetailDO.setCreateUserId(userId);
        customerFundDetailDO.setUpdateTime(DateUtils.getCurrentDate());
        customerFundDetailDO.setUpdateUserId(userId);
        customerFundDetailDO.setUnit(user.getUnit());
        int addOneResult = iCustomerFundDetailService.addOne(customerFundDetailDO);
        LOGGER.info("添加提现流水：{}，结果：{}", customerFundDetailDO, addOneResult);
        if (addOneResult <= 0) {
            throw new BusinessException(ResponseEnum.ADD_CUSTOMER_FUND_DETAIL_FAILED);
        }
        alarmTools.alert("APP", "用户资金", "提现", "用户【" + user.getUserName() + "】充值【" + depositMoney + "元】成功");
    }

    /**
     * ########## 充值 ##########
     *
     * @param offlinePayChekDO 充值信息
     */
    private void rechargeMoney(OfflinePayChekDO offlinePayChekDO) {
        if (!OfflineCheckStatusEnum.PASS.getValue().equals(offlinePayChekDO.getCheckStatus())) {
            return;
        }
        String userId = offlinePayChekDO.getUserId();
        UserDO user = iUserService.findUser(new UserDO(userId));
        AgentDO query = new AgentDO();
        query.setMobile(user.getInviterMobile());
        AgentDO agent = iAgentService.findAgent(query);
        /**
         * 更新用户资金信息
         */
        BigDecimal happenMoney = offlinePayChekDO.getHappenMoney();
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(new CustomerFundDO(userId));
        CustomerFundDO update = new CustomerFundDO(userId);
        update.setAccountTotalMoney(BigDecimalUtils.add(customerFund.getAccountTotalMoney(), happenMoney));// 账户总金额
        update.setUsableMoney(BigDecimalUtils.add(customerFund.getUsableMoney(), happenMoney));// 可用金额
        update.setTotalRechargeMoney(BigDecimalUtils.add(update.getTotalRechargeMoney(), happenMoney));// 累计充值
        int result = iCustomerFundService.updateByPrimaryKeySelective(update);
        LOGGER.info("更新用户资金信息：{}，结果：{}", update, result);
        if (result <= 0) {
            throw new BusinessException(ResponseEnum.ADD_CUSTOMER_FUND_FAILED);
        }
        /**
         * 插入流水
         */
        CustomerFundDetailDO customerFundDetailDO = new CustomerFundDetailDO();
        customerFundDetailDO.setUserId(userId);
        customerFundDetailDO.setUserName(user.getUserName());
        customerFundDetailDO.setAgentId(agent.getAgentId());
        customerFundDetailDO.setAgentName(agent.getAgentName());
        customerFundDetailDO.setHappenMoney(happenMoney);// 发生金额
        customerFundDetailDO.setAfterHappenMoney(happenMoney);// 发生后金额
        customerFundDetailDO.setFundType(FundTypeEnum.RECHARGE.getValue());// 资金类型
        customerFundDetailDO.setRemark(FundTypeEnum.RECHARGE.getDesc());// 备注
        customerFundDetailDO.setCreateTime(DateUtils.getCurrentDate());
        customerFundDetailDO.setCreateUserId(userId);
        customerFundDetailDO.setUpdateTime(DateUtils.getCurrentDate());
        customerFundDetailDO.setUpdateUserId(userId);
        customerFundDetailDO.setUnit(user.getUnit());
        int addOneResult = iCustomerFundDetailService.addOne(customerFundDetailDO);
        LOGGER.info("添加充值流水：{}，结果：{}", customerFundDetailDO, addOneResult);
        if (addOneResult <= 0) {
            throw new BusinessException(ResponseEnum.ADD_CUSTOMER_FUND_DETAIL_FAILED);
        }
        alarmTools.alert("APP", "用户资金", "充值", "用户【" + user.getUserName() + "】充值【" + happenMoney + "元】成功");
    }

    @Override
    public int deleteById(Integer checkId) {
        return offlinePayCheckMapper.deleteByPrimaryKey(checkId);
    }

}
