package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

import java.math.BigDecimal;

/**
 * ========== 代理商资金 ==========
 *
 * @author Mr.huang
 * @version AgentFundDO.java, v1.0
 * @date 2019年06月09日 10时12分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_agent_fund", comment = "代理商资金")
public class AgentFundDO extends BaseDO {

    private static final long serialVersionUID = 2104128370207391370L;

    /**
     * 代理商编号
     */
    @SelfTableColumn(value = "agentId", id = true, comment = "代理商编号")
    private String agentId;

    /**
     * 代理商名称
     */
    @SelfTableColumn(value = "agentName", comment = "代理商名称")
    private String agentName;

    /**
     * 账户总金额
     */
    @SelfTableColumn(value = "accountTotalMoney", length = 12, defaultValue = "0", comment = "账户总金额")
    private BigDecimal accountTotalMoney;

    /**
     * 冻结金额
     */
    @SelfTableColumn(value = "freezeMoney", length = 12, defaultValue = "0", comment = "冻结金额")
    private BigDecimal freezeMoney;

    /**
     * 可用余额
     */
    @SelfTableColumn(value = "usableMoney", length = 12, defaultValue = "0", comment = "可用余额")
    private BigDecimal usableMoney;

    /**
     * 备注
     */
    @SelfTableColumn(value = "remark", comment = "备注")
    private String remark;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AgentFundDO{" +
                "agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", accountTotalMoney=" + accountTotalMoney +
                ", freezeMoney=" + freezeMoney +
                ", usableMoney=" + usableMoney +
                ", remark='" + remark + '\'' +
                '}' + "," + super.toString();
    }
}
