package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

/**
 * ========== 线下支付审核 ==========
 *
 * @author Mr.huang
 * @version OfflinePayChekDO.java, v1.0
 * @date 2019年06月09日 11时24分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OfflinePayChekDO extends BaseDO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -554520087138949757L;

    /**
     * 编号
     */
    private Integer checkId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 发生金额
     */
    private BigDecimal happenMoney;

    /**
     * 支付渠道
     */
    private String payChannel;

    /**
     * 审核状态
     */
    private String checkStatus;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 管理员备注
     */
    private String systemRemark;

    public OfflinePayChekDO() {
    }

    public OfflinePayChekDO(String userId) {
        this.userId = userId;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getHappenMoney() {
        return happenMoney;
    }

    public void setHappenMoney(BigDecimal happenMoney) {
        this.happenMoney = happenMoney;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSystemRemark() {
        return systemRemark;
    }

    public void setSystemRemark(String systemRemark) {
        this.systemRemark = systemRemark;
    }

    @Override
    public String toString() {
        return "OfflinePayChekDO{" +
                "checkId=" + checkId +
                ", userId='" + userId + '\'' +
                ", happenMoney=" + happenMoney +
                ", payChannel='" + payChannel + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", systemRemark='" + systemRemark + '\'' +
                '}' + super.toString();
    }
}
