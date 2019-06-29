package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

/**
 * ========== 代理商资金流水明细 ==========
 *
 * @author Mr.huang
 * @version AgentFundDeailDO.java, v1.0
 * @date 2019年06月09日 10时32分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AgentFundDeailDO extends BaseDO {

    private static final long serialVersionUID = -1052328982243050208L;

    /**
     * 流水编号
     */
    private Integer detailId;

    /**
     * 代理商编号
     */
    private String agentId;

    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 发生金额
     */
    private BigDecimal money;

    /**
     * 资金类型
     */
    private String type;

    /**
     * 备注
     */
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
