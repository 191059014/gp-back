package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * ========== 客户资金流水 ==========
 *
 * @author Mr.huang
 * @version CustomerFundDetailDO.java, v1.0
 * @date 2019年06月09日 10时58分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "用户资金流水模型")
@SelfTableClass(value = "t_customer_fund_detail", comment = "客户资金流水")
public class CustomerFundDetailDO extends BaseDO {

    private static final long serialVersionUID = -4338910585836548024L;

    @ApiModelProperty(value = "流水编号")
    @SelfTableColumn(value = "detailId", id = true, comment = "流水编号")
    private String detailId;

    @ApiModelProperty(value = "客户编号")
    @SelfTableColumn(value = "userId", comment = "客户编号")
    private String userId;

    @ApiModelProperty(value = "客户名称")
    @SelfTableColumn(value = "userName", comment = "客户名称")
    private String userName;

    @ApiModelProperty(value = "代理商编号")
    @SelfTableColumn(value = "agentId", comment = "代理商编号")
    private String agentId;

    @ApiModelProperty(value = "代理商名称")
    @SelfTableColumn(value = "agentName", comment = "代理商名称")
    private String agentName;

    @ApiModelProperty(value = "发生金额")
    @SelfTableColumn(value = "happenMoney", length = 12, defaultValue = "0", comment = "发生金额")
    private BigDecimal happenMoney;

    @ApiModelProperty(value = "发生后金额")
    @SelfTableColumn(value = "afterHappenMoney", length = 12, defaultValue = "0", comment = "发生后金额")
    private BigDecimal afterHappenMoney;

    @ApiModelProperty(value = "资金类型")
    @SelfTableColumn(value = "fundType", length = 1, comment = "资金类型")
    private Integer fundType;

    @ApiModelProperty(value = "审核状态")
    @SelfTableColumn(value = "checkStatus", length = 1, comment = "审核状态")
    private Integer checkStatus;

    @ApiModelProperty(value = "备注")
    @SelfTableColumn(value = "remark", comment = "备注")
    private String remark;

    public CustomerFundDetailDO() {
    }

    public CustomerFundDetailDO(String detailId) {
        this.detailId = detailId;
    }

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

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
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
                ", checkStatus='" + checkStatus + '\'' +
                ", remark='" + remark + '\'' +
                '}' + "," + super.toString();
    }
}
