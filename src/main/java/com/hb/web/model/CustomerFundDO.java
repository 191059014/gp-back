package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

/**
 * ========== 客户资金信息 ==========
 *
 * @author Mr.huang
 * @version CustomerFundDO.java, v1.0
 * @date 2019年06月09日 10时44分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerFundDO extends BaseDO {

    private static final long serialVersionUID = 5605560815676115501L;

    /**
     * 客户编号
     */
    private String userId;

    /**
     * 客户姓名
     */
    private String userName;

    /**
     * 代理商编号
     */
    private String agentId;

    /**
     * 代理商姓名
     */
    private String agentName;

    /**
     * 账户总金额
     */
    private BigDecimal accountTotalMoney;

    /**
     * 冻结金额
     */
    private BigDecimal freezeMoney;

    /**
     * 可用余额
     */
    private BigDecimal usableMoney;

    /**
     * 累计充值
     */
    private BigDecimal totalRechargeMoney;

    /**
     * 累计提现
     */
    private BigDecimal totalWithdrawMoney;

    /**
     * 累计盈亏
     */
    private BigDecimal totalProfitAndLossMoney;

    /**
     * 累计信息服务费
     */
    private BigDecimal totalMessageServiceMoney;

    /**
     * 累计出入金额
     */
    private BigDecimal totalInAndOutMoney;

    public CustomerFundDO() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public CustomerFundDO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public BigDecimal getAccountTotalMoney() {
        return accountTotalMoney;
    }

    public void setAccountTotalMoney(BigDecimal accountTotalMoney) {
        this.accountTotalMoney = accountTotalMoney;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public BigDecimal getUsableMoney() {
        return usableMoney;
    }

    public void setUsableMoney(BigDecimal usableMoney) {
        this.usableMoney = usableMoney;
    }

    public BigDecimal getTotalRechargeMoney() {
        return totalRechargeMoney;
    }

    public void setTotalRechargeMoney(BigDecimal totalRechargeMoney) {
        this.totalRechargeMoney = totalRechargeMoney;
    }

    public BigDecimal getTotalWithdrawMoney() {
        return totalWithdrawMoney;
    }

    public void setTotalWithdrawMoney(BigDecimal totalWithdrawMoney) {
        this.totalWithdrawMoney = totalWithdrawMoney;
    }

    public BigDecimal getTotalProfitAndLossMoney() {
        return totalProfitAndLossMoney;
    }

    public void setTotalProfitAndLossMoney(BigDecimal totalProfitAndLossMoney) {
        this.totalProfitAndLossMoney = totalProfitAndLossMoney;
    }

    public BigDecimal getTotalMessageServiceMoney() {
        return totalMessageServiceMoney;
    }

    public void setTotalMessageServiceMoney(BigDecimal totalMessageServiceMoney) {
        this.totalMessageServiceMoney = totalMessageServiceMoney;
    }

    public BigDecimal getTotalInAndOutMoney() {
        return totalInAndOutMoney;
    }

    public void setTotalInAndOutMoney(BigDecimal totalInAndOutMoney) {
        this.totalInAndOutMoney = totalInAndOutMoney;
    }

    @Override
    public String toString() {
        return "CustomerFundDO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", accountTotalMoney=" + accountTotalMoney +
                ", freezeMoney=" + freezeMoney +
                ", usableMoney=" + usableMoney +
                ", totalRechargeMoney=" + totalRechargeMoney +
                ", totalWithdrawMoney=" + totalWithdrawMoney +
                ", totalProfitAndLossMoney=" + totalProfitAndLossMoney +
                ", totalMessageServiceMoney=" + totalMessageServiceMoney +
                ", totalInAndOutMoney=" + totalInAndOutMoney +
                '}' + "," + super.toString();
    }

    /**
     * ########## 初始化资金信息 ##########
     */
    public void preHandler() {
        this.setTotalRechargeMoney(BigDecimal.ZERO);
        this.setUsableMoney(BigDecimal.ZERO);
        this.setAccountTotalMoney(BigDecimal.ZERO);
        this.setFreezeMoney(BigDecimal.ZERO);
        this.setTotalInAndOutMoney(BigDecimal.ZERO);
        this.setTotalMessageServiceMoney(BigDecimal.ZERO);
        this.setTotalProfitAndLossMoney(BigDecimal.ZERO);
        this.setTotalWithdrawMoney(BigDecimal.ZERO);
    }

}
