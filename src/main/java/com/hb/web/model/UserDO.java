package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

/**
 * ========== 用户实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.UserDO.java, v1.0
 * @date 2019年06月03日 11时15分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_user", comment = "用户表")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = -6318454204873782496L;
    /**
     * 用户编号
     */
    @SelfTableColumn(value = "userId", id = true, comment = "用户编号")
    private String userId;

    /**
     * 用户名
     */
    @SelfTableColumn(value = "userName", comment = "用户名")
    private String userName;

    /**
     * 登录密码
     */
    @SelfTableColumn(value = "password", comment = "登录密码")
    private String password;

    /**
     * 银行名称
     */
    @SelfTableColumn(value = "bankName", comment = "银行名称")
    private String bankName;

    /**
     * 银行卡号
     */
    @SelfTableColumn(value = "bankNo", length = 19, comment = "银行卡号")
    private String bankNo;

    /**
     * 身份证号
     */
    @SelfTableColumn(value = "idCardNo", length = 18, comment = "身份证号")
    private String idCardNo;

    /**
     * 实名认证状态
     */
    @SelfTableColumn(value = "realAuthStatus", length = 1, defaultValue = "0", comment = "实名认证状态")
    private Integer realAuthStatus;

    /**
     * 手机号
     */
    @SelfTableColumn(value = "mobile", length = 11, comment = "手机号")
    private String mobile;

    /**
     * 邀请人手机号码
     */
    @SelfTableColumn(value = "inviterMobile", length = 11, comment = "邀请人手机号码")
    private String inviterMobile;

    /**
     * 风险评测等级
     */
    @SelfTableColumn(value = "riskLevel", length = 1, defaultValue = "'N'", comment = "风险评测等级")
    private String riskLevel;

    /**
     * 风险评测得分
     */
    @SelfTableColumn(value = "riskScore", length = 3, defaultValue = "0", comment = "风险评测得分")
    private Integer riskScore;

    public UserDO() {
    }

    public UserDO(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Integer getRealAuthStatus() {
        return realAuthStatus;
    }

    public void setRealAuthStatus(Integer realAuthStatus) {
        this.realAuthStatus = realAuthStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInviterMobile() {
        return inviterMobile;
    }

    public void setInviterMobile(String inviterMobile) {
        this.inviterMobile = inviterMobile;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public Integer getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Integer riskScore) {
        this.riskScore = riskScore;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", realAuthStatus='" + realAuthStatus + '\'' +
                ", mobile='" + mobile + '\'' +
                ", inviterMobile=" + inviterMobile +
                ", riskLevel='" + riskLevel + '\'' +
                ", riskScore='" + riskScore + '\'' +
                '}' + "," + super.toString();
    }
}
