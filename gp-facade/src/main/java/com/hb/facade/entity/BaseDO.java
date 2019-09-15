package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableColumn;
import com.hb.unic.util.util.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * ========== 基层EO ==========
 *
 * @author Mr.huang
 * @version BaseDO.java, v1.0
 * @date 2019年06月09日 08时48分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    @SelfTableColumn(value = "createTime", defaultValue = "CURRENT_TIMESTAMP", comment = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @SelfTableColumn(value = "createUserId", comment = "创建人")
    private String createUserId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    @SelfTableColumn(value = "updateTime", defaultValue = "CURRENT_TIMESTAMP", comment = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @SelfTableColumn(value = "updateUserId", comment = "更新人")
    private String updateUserId;

    /**
     * 数据有效状态
     */
    @SelfTableColumn(value = "recordStatus", length = 1, defaultValue = "1", comment = "数据有效状态")
    private Integer recordStatus;

    /**
     * 父级ID
     */
    @SelfTableColumn(value = "parentId", comment = "父级ID")
    private String parentId;

    /**
     * 数据分区
     */
    @SelfTableColumn(value = "unit", length = 5, comment = "数据分区")
    private Integer unit;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "BaseDO{" +
                "createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserId='" + updateUserId + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                ", parentId='" + parentId + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
