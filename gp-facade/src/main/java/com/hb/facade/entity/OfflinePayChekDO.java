package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

import java.math.BigDecimal;

/**
 * ========== 线下支付审核 ==========
 *
 * @author Mr.huang
 * @version OfflinePayChekDO.java, v1.0
 * @date 2019年06月09日 11时24分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_offline_pay_check", comment = "线下支付审核")
public class OfflinePayChekDO extends BaseDO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -554520087138949757L;

    /**
     * 审核编号
     */
    @SelfTableColumn(value = "checkId", id = true, comment = "审核编号")
    private Integer checkId;

    /**
     * 用户ID
     */
    @SelfTableColumn(value = "userId", comment = "用户ID")
    private String userId;

    /**
     * 发生金额
     */
    @SelfTableColumn(value = "happenMoney", length = 12, defaultValue = "0", comment = "发生金额")
    private BigDecimal happenMoney;

    /**
     * 支付渠道
     */
    @SelfTableColumn(value = "payChannel", length = 1, comment = "支付渠道")
    private Integer payChannel;

    /**
     * 审核状态
     */
    @SelfTableColumn(value = "checkStatus", length = 1, comment = "审核状态")
    private Integer checkStatus;

    /**
     * 支付状态
     */
    @SelfTableColumn(value = "payStatus", length = 1, comment = "支付状态")
    private Integer payStatus;

    /**
     * 资金类型
     */
    @SelfTableColumn(value = "fundType", length = 1, comment = "资金类型")
    private Integer fundType;

    /**
     * 备注
     */
    @SelfTableColumn(value = "remark", comment = "备注")
    private String remark;

    /**
     * 管理员备注
     */
    @SelfTableColumn(value = "systemRemark", comment = "管理员备注")
    private String systemRemark;

    /**
     * 资金明细ID
     */
    @SelfTableColumn(value = "detailId", comment = "资金明细ID")
    private String detailId;

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

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
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

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
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
                ", fundType='" + fundType + '\'' +
                ", remark='" + remark + '\'' +
                ", systemRemark='" + systemRemark + '\'' +
                ", detailId='" + detailId + '\'' +
                '}' + super.toString();
    }
}
