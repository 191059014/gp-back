package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.web.util.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * ========== 基层EO ==========
 *
 * @author Mr.huang
 * @version BaseDO.java, v1.0
 * @date 2019年06月09日 08时48分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseDO implements Serializable {

    /**
     * 创建时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    private Date createTime;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUserId;

    /**
     * 数据有效状态
     */
    private String recordStatus;

    /**
     * 父级ID
     */
    private String parentId;

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

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
                '}';
    }
}
