package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

import java.math.BigDecimal;

/**
 * ========== 代理商资金流水明细 ==========
 *
 * @author Mr.huang
 * @version AgentFundDeailDO.java, v1.0
 * @date 2019年06月09日 10时32分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_agent_fund_detail", comment = "代理商资金流水明细")
public class AgentFundDeailDO extends BaseDO {

    private static final long serialVersionUID = -1052328982243050208L;

    /**
     * 流水编号
     */
    @SelfTableColumn(value = "detailId", id = true, comment = "流水编号")
    private Integer detailId;

    /**
     * 代理商编号
     */
    @SelfTableColumn(value = "agentId", comment = "代理商编号")
    private String agentId;

    /**
     * 代理商名称
     */
    @SelfTableColumn(value = "agentName", comment = "代理商名称")
    private String agentName;

    /**
     * 发生金额
     */
    @SelfTableColumn(value = "money", length = 12, defaultValue = "0", comment = "发生金额")
    private BigDecimal money;

    /**
     * 资金类型
     */
    @SelfTableColumn(value = "type", length = 1, comment = "资金类型")
    private Integer type;

    /**
     * 备注
     */
    @SelfTableColumn(value = "remark", comment = "备注")
    private String remark;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AgentFundDeailDO{" +
                "detailId=" + detailId +
                ", agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", money=" + money +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                '}' + "," + super.toString();
    }
}
