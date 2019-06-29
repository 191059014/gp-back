package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

/**
 * ========== 客户资金流水 ==========
 *
 * @author Mr.huang
 * @version CustomerFundDetailDO.java, v1.0
 * @date 2019年06月09日 10时58分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerFundDetailDO extends BaseDO {

    private static final long serialVersionUID = -4338910585836548024L;

    /**
     * 流水编号
     */
    private String detailId;

    /**
     * 客户编号
     */
    private String userId;

    /**
     * 客户名
     */
    private String userName;

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
    private BigDecimal happenMoney;

    /**
     * 发生后金额
     */
    private BigDecimal afterHappenMoney;

    /**
     * 资金类型
     */
    private String fundType;

    /**
     * 备注
     */
    private String remark;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public BigDecimal getHappenMoney() {
        return happenMoney;
    }

    public void setHappenMoney(BigDecimal happenMoney) {
        this.happenMoney = happenMoney;
    }

    public BigDecimal getAfterHappenMoney() {
        return afterHappenMoney;
    }

    public void setAfterHappenMoney(BigDecimal afterHappenMoney) {
        this.afterHappenMoney = afterHappenMoney;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CustomerFundDetailDO{" +
                "detailId='" + detailId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", happenMoney='" + happenMoney + '\'' +
                ", afterHappenMoney='" + afterHappenMoney + '\'' +
                ", fundType='" + fundType + '\'' +
                ", remark='" + remark + '\'' +
                '}' + "," + super.toString();
    }
}
