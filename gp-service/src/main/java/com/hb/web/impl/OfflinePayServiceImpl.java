package com.hb.web.impl;

import com.hb.facade.common.ResponseEnum;
import com.hb.facade.entity.CustomerFundDO;
import com.hb.facade.entity.CustomerFundDetailDO;
import com.hb.facade.entity.OfflinePayChekDO;
import com.hb.facade.entity.UserDO;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

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

    @Value("${gpweb.unit}")
    private Integer unit;

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
        offlinePayChekDO.setUnit(unit);
        return offlinePayCheckMapper.findList(offlinePayChekDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public List<OfflinePayChekDO> findAppList(OfflinePayChekDO offlinePayChekDO, Integer startRow, Integer pageSize) {
        return offlinePayCheckMapper.findList(offlinePayChekDO, startRow, pageSize);
    }

    @Override
    public Integer findCount(OfflinePayChekDO offlinePayChekDO) {
        offlinePayChekDO.setUnit(unit);
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(OfflinePayChekDO offlinePayChekDO) {
        if (unit == null) {
            if (FundTypeEnum.RECHARGE.getValue().equals(offlinePayChekDO.getFundType())) {
                // 充值
                rechargeMoney(offlinePayChekDO);
            } else if (FundTypeEnum.DEPOSIT.getValue().equals(offlinePayChekDO.getFundType())) {
                // 提现
                depositMoney(offlinePayChekDO);
            }
        }
        // 更新线下支付信息
        if (OfflineCheckStatusEnum.PASS.getValue().equals(offlinePayChekDO.getCheckStatus())) {
            offlinePayChekDO.setPayStatus(OfflinePayStatusEnum.ALREADY_PAY.getValue());
        }
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
        String userId = offlinePayChekDO.getUserId();
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(new CustomerFundDO(userId));
        LOGGER.info("查询用户的资金信息：{}", customerFund);
        if (customerFund == null) {
            throw new BusinessException(ResponseEnum.NO_FUND_INFO);
        }
        BigDecimal happenMoney = offlinePayChekDO.getHappenMoney();
        BigDecimal usableMoney = customerFund.getUsableMoney();
        BigDecimal freezeMoney = customerFund.getFreezeMoney();
        if (BigDecimal.ZERO.compareTo(usableMoney) > 0) {
            LOGGER.info(LogUtils.appLog("账户余额不足"));
            throw new BusinessException(ResponseEnum.NOT_ENOUGH_USEABLE_MONEY);
        }
        UserDO user = iUserService.findUser(new UserDO(userId));
        if (OfflineCheckStatusEnum.PASS.getValue().equals(offlinePayChekDO.getCheckStatus())) {
            /**
             * 更新用户资金信息
             */
            customerFund.setUpdateTime(new Date());
            // 总资产减少
            customerFund.setAccountTotalMoney(BigDecimalUtils.subtract(customerFund.getAccountTotalMoney(), happenMoney));
            // 冻结资金减少
            customerFund.setFreezeMoney(BigDecimalUtils.subtract(customerFund.getFreezeMoney(), happenMoney));
            // 累积出入金额
            customerFund.setTotalInAndOutMoney(BigDecimalUtils.add(customerFund.getTotalInAndOutMoney(), happenMoney));
            int result = iCustomerFundService.updateByPrimaryKeySelective(customerFund);
            LOGGER.info("更新用户资金信息：{}，结果：{}", customerFund, result);
            if (result <= 0) {
                throw new BusinessException(ResponseEnum.ADD_CUSTOMER_FUND_FAILED);
            }
            /**
             * 更新流水
             */
            CustomerFundDetailDO customerFundDetailDO = iCustomerFundDetailService.findOne(new CustomerFundDetailDO(offlinePayChekDO.getDetailId()));
            customerFundDetailDO.setUpdateTime(DateUtils.getCurrentDate());
            customerFundDetailDO.setUpdateUserId(userId);
            customerFundDetailDO.setUnit(user.getUnit());
            customerFundDetailDO.setCheckStatus(offlinePayChekDO.getCheckStatus());
            int updateResult = iCustomerFundDetailService.updateByPrimaryKeySelective(customerFundDetailDO);
            LOGGER.info("更新提现流水：{}，结果：{}", customerFundDetailDO, updateResult);
            if (updateResult <= 0) {
                throw new BusinessException(ResponseEnum.UPDATE_CUSTOMER_FUND_DETAIL_FAILED);
            }
            alarmTools.alert("WEB", "用户资金", "提现", "用户【" + user.getUserName() + "】提现【" + happenMoney + "元】成功");
        } else if (OfflineCheckStatusEnum.REJECT.getValue().equals(offlinePayChekDO.getCheckStatus())) {
            /**
             * 恢复冻结资金到可用余额
             */
            // 冻结资金减少
            customerFund.setFreezeMoney(BigDecimalUtils.subtract(freezeMoney, happenMoney));
            // 可用余额增加
            customerFund.setUsableMoney(BigDecimalUtils.add(usableMoney, happenMoney));
            iCustomerFundService.updateByPrimaryKeySelective(customerFund);
            /**
             * 更新流水
             */
            CustomerFundDetailDO customerFundDetailDO = iCustomerFundDetailService.findOne(new CustomerFundDetailDO(offlinePayChekDO.getDetailId()));
            customerFundDetailDO.setUpdateTime(DateUtils.getCurrentDate());
            customerFundDetailDO.setUpdateUserId(userId);
            customerFundDetailDO.setUnit(user.getUnit());
            customerFundDetailDO.setCheckStatus(offlinePayChekDO.getCheckStatus());
            int updateResult = iCustomerFundDetailService.updateByPrimaryKeySelective(customerFundDetailDO);
            LOGGER.info("更新提现流水：{}，结果：{}", customerFundDetailDO, updateResult);
            if (updateResult <= 0) {
                throw new BusinessException(ResponseEnum.UPDATE_CUSTOMER_FUND_DETAIL_FAILED);
            }
        }

    }

    /**
     * ########## 充值 ##########
     *
     * @param offlinePayChekDO 充值信息
     */
    private void rechargeMoney(OfflinePayChekDO offlinePayChekDO) {
        if (OfflineCheckStatusEnum.AUDITING.getValue().equals(offlinePayChekDO.getCheckStatus())) {
            return;
        }
        String userId = offlinePayChekDO.getUserId();
        UserDO user = iUserService.findUser(new UserDO(userId));
        /**
         * 更新用户资金信息
         */
        BigDecimal happenMoney = offlinePayChekDO.getHappenMoney();
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(new CustomerFundDO(userId));
        CustomerFundDO update = new CustomerFundDO(userId);
        update.setAccountTotalMoney(BigDecimalUtils.add(customerFund.getAccountTotalMoney(), happenMoney));// 账户总金额
        update.setUsableMoney(BigDecimalUtils.add(customerFund.getUsableMoney(), happenMoney));// 可用金额
        update.setTotalRechargeMoney(BigDecimalUtils.add(customerFund.getTotalRechargeMoney(), happenMoney));// 累计充值
        update.setTotalInAndOutMoney(BigDecimalUtils.add(customerFund.getTotalInAndOutMoney(), happenMoney));// 累积出入金额
        int result = iCustomerFundService.updateByPrimaryKeySelective(update);
        LOGGER.info("更新用户资金信息：{}，结果：{}", update, result);
        if (result <= 0) {
            throw new BusinessException(ResponseEnum.ADD_CUSTOMER_FUND_FAILED);
        }
        /**
         * 更新流水
         */
        CustomerFundDetailDO customerFundDetailDO = iCustomerFundDetailService.findOne(new CustomerFundDetailDO(offlinePayChekDO.getDetailId()));
        customerFundDetailDO.setUpdateTime(DateUtils.getCurrentDate());
        customerFundDetailDO.setUpdateUserId(userId);
        customerFundDetailDO.setUnit(user.getUnit());
        customerFundDetailDO.setCheckStatus(offlinePayChekDO.getCheckStatus());
        int updateResult = iCustomerFundDetailService.updateByPrimaryKeySelective(customerFundDetailDO);
        LOGGER.info("更新充值流水：{}，结果：{}", customerFundDetailDO, updateResult);
        if (updateResult <= 0) {
            throw new BusinessException(ResponseEnum.UPDATE_CUSTOMER_FUND_DETAIL_FAILED);
        }
        alarmTools.alert("WEB", "用户资金", "充值", "用户【" + user.getUserName() + "】充值【" + happenMoney + "元】成功");
    }

    @Override
    public int deleteById(Integer checkId) {
        return offlinePayCheckMapper.deleteByPrimaryKey(checkId);
    }

}
