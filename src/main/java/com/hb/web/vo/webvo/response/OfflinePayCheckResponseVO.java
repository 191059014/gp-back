package com.hb.web.vo.webvo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb.web.util.DateUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ========== 审核信息-请求vo ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.webvo.response.OfflinePayCheckResponseVO.java, v1.0
 * @date 2019年06月24日 23时30分
 */
public class OfflinePayCheckResponseVO implements Serializable {

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
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String mobile;

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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    private Date createTime;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "OfflinePayCheckResponseVO{" +
                "checkId=" + checkId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", happenMoney=" + happenMoney +
                ", payChannel='" + payChannel + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", systemRemark='" + systemRemark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
