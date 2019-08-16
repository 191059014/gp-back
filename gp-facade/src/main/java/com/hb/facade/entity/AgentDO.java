package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

/**
 * ========== 代理商 ==========
 *
 * @author Mr.huang
 * @version AgentDO.java, v1.0
 * @date 2019年06月16日 12时06分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_agent", comment = "代理商")
public class AgentDO extends BaseDO {

    private static final long serialVersionUID = 3960685371501045357L;

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
     * 密码
     */
    @SelfTableColumn(value = "password", comment = "密码")
    private String password;

    /**
     * 代理商等级
     */
    @SelfTableColumn(value = "agentLevel", length = 1, comment = "代理商等级")
    private Integer agentLevel;

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

    public Integer getAgentLevel() {
        return agentLevel;
    }

    public void setAgentLevel(Integer agentLevel) {
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
                '}' + "," + super.toString();
    }
}
