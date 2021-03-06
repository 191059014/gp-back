package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * ========== 客户资金信息 ==========
 *
 * @author Mr.huang
 * @version CustomerFundDO.java, v1.0
 * @date 2019年06月09日 10时44分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "用户资金模型")
@SelfTableClass(value = "t_customer_fund", comment = "客户资金信息")
public class CustomerFundDO extends BaseDO {

    private static final long serialVersionUID = 5605560815676115501L;

    @ApiModelProperty(value = "客户编号")
    @SelfTableColumn(value = "userId", id = true, comment = "用户编号")
    private String userId;

    @ApiModelProperty(value = "客户姓名")
    @SelfTableColumn(value = "userName", comment = "用户名")
    private String userName;

    @ApiModelProperty(value = "代理商编号")
    @SelfTableColumn(value = "agentId", comment = "代理商编号")
    private String agentId;

    @ApiModelProperty(value = "代理商姓名")
    @SelfTableColumn(value = "agentName", comment = "代理商名称")
    private String agentName;

    @ApiModelProperty(value = "账户总金额")
    @SelfTableColumn(value = "accountTotalMoney", length = 12, defaultValue = "0", comment = "账户总金额")
    private BigDecimal accountTotalMoney;

    @ApiModelProperty(value = "冻结金额")
    @SelfTableColumn(value = "freezeMoney", length = 12, defaultValue = "0", comment = "冻结金额")
    private BigDecimal freezeMoney;

    @ApiModelProperty(value = "交易冻结金额")
    @SelfTableColumn(value = "tradeFreezeMoney", length = 12, defaultValue = "0", comment = "交易冻结金额")
    private BigDecimal tradeFreezeMoney;

    @ApiModelProperty(value = "可用余额")
    @SelfTableColumn(value = "usableMoney", length = 12, defaultValue = "0", comment = "可用余额")
    private BigDecimal usableMoney;

    @ApiModelProperty(value = "累计充值")
    @SelfTableColumn(value = "totalRechargeMoney", length = 12, defaultValue = "0", comment = "累计充值")
    private BigDecimal totalRechargeMoney;

    @ApiModelProperty(value = "累计提现")
    @SelfTableColumn(value = "totalWithdrawMoney", length = 12, defaultValue = "0", comment = "累计提现")
    private BigDecimal totalWithdrawMoney;

    @ApiModelProperty(value = "累计盈亏")
    @SelfTableColumn(value = "totalProfitAndLossMoney", length = 12, defaultValue = "0", comment = "累计盈亏")
    private BigDecimal totalProfitAndLossMoney;

    @ApiModelProperty(value = "累计信息服务费")
    @SelfTableColumn(value = "totalMessageServiceMoney", length = 12, defaultValue = "0", comment = "累计信息服务费")
    private BigDecimal totalMessageServiceMoney;

    @ApiModelProperty(value = "累计出入金额")
    @SelfTableColumn(value = "totalInAndOutMoney", length = 12, defaultValue = "0", comment = "累计出入金额")
    private BigDecimal totalInAndOutMoney;

    @ApiModelProperty(value = "累计持仓市值总金额")
    @SelfTableColumn(value = "totalStrategyMoney", length = 12, defaultValue = "0", comment = "累计出入金额")
    private BigDecimal totalStrategyMoney;

    @ApiModelProperty(value = "累计持仓信用金总金额")
    @SelfTableColumn(value = "totalStrategyOwnMoney", length = 12, defaultValue = "0", comment = "累计出入金额")
    private BigDecimal totalStrategyOwnMoney;

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

    public BigDecimal getTradeFreezeMoney() {
        return tradeFreezeMoney;
    }

    public void setTradeFreezeMoney(BigDecimal tradeFreezeMoney) {
        this.tradeFreezeMoney = tradeFreezeMoney;
    }

    public BigDecimal getTotalStrategyMoney() {
        return totalStrategyMoney;
    }

    public void setTotalStrategyMoney(BigDecimal totalStrategyMoney) {
        this.totalStrategyMoney = totalStrategyMoney;
    }

    public BigDecimal getTotalStrategyOwnMoney() {
        return totalStrategyOwnMoney;
    }

    public void setTotalStrategyOwnMoney(BigDecimal totalStrategyOwnMoney) {
        this.totalStrategyOwnMoney = totalStrategyOwnMoney;
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
                ", tradeFreezeMoney=" + tradeFreezeMoney +
                ", usableMoney=" + usableMoney +
                ", totalRechargeMoney=" + totalRechargeMoney +
                ", totalWithdrawMoney=" + totalWithdrawMoney +
                ", totalProfitAndLossMoney=" + totalProfitAndLossMoney +
                ", totalMessageServiceMoney=" + totalMessageServiceMoney +
                ", totalInAndOutMoney=" + totalInAndOutMoney +
                ", totalStrategyMoney=" + totalStrategyMoney +
                ", totalStrategyOwnMoney=" + totalStrategyOwnMoney +
                '}' + super.toString();
    }

    /**
     * ########## 初始化资金信息 ##########
     */
    public void preHandler() {
        this.setTotalRechargeMoney(BigDecimal.ZERO);
        this.setUsableMoney(BigDecimal.ZERO);
        this.setAccountTotalMoney(BigDecimal.ZERO);
        this.setFreezeMoney(BigDecimal.ZERO);
        this.setTradeFreezeMoney(BigDecimal.ZERO);
        this.setTotalInAndOutMoney(BigDecimal.ZERO);
        this.setTotalMessageServiceMoney(BigDecimal.ZERO);
        this.setTotalProfitAndLossMoney(BigDecimal.ZERO);
        this.setTotalWithdrawMoney(BigDecimal.ZERO);
        this.setTotalMessageServiceMoney(BigDecimal.ZERO);
        this.setTotalStrategyMoney(BigDecimal.ZERO);
        this.setTotalStrategyOwnMoney(BigDecimal.ZERO);
    }

}
