package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ========== 代理商 ==========
 *
 * @author Mr.huang
 * @version AgentDO.java, v1.0
 * @date 2019年06月16日 12时06分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AgentDO extends BaseDO {

    private static final long serialVersionUID = 3960685371501045357L;

    /**
     * 代理商编号
     */
    private String agentId;

    /**
     * 代理商名称
     */
    private String agentName;

    /**
     * 密码
     */
    private String password;

    /**
     * 代理商等级
     */
    private String agentLevel;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String bankNo;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 实名认证状态
     */
    private String realAuthStatus;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 代理商编制
     */
    private String agentUnit;

    public AgentDO() {
    }

    public AgentDO(String agentId) {
        this.agentId = agentId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(String agentLevel) {
        this.agentLevel = agentLevel;
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

    public String getRealAuthStatus() {
        return realAuthStatus;
    }

    public void setRealAuthStatus(String realAuthStatus) {
        this.realAuthStatus = realAuthStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAgentUnit() {
        return agentUnit;
    }

    public void setAgentUnit(String agentUnit) {
        this.agentUnit = agentUnit;
    }

    @Override
    public String toString() {
        return "AgentDO{" +
                "agentId='" + agentId + '\'' +
                ", agentName='" + agentName + '\'' +
                ", password='" + password + '\'' +
                ", agentLevel='" + agentLevel + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", realAuthStatus='" + realAuthStatus + '\'' +
                ", mobile='" + mobile + '\'' +
                ", agentUnit=" + agentUnit +
                '}' + "," + super.toString();
    }
}
