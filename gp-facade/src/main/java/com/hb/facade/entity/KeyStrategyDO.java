package com.hb.facade.entity;

import com.hb.unic.base.annotation.SelfTableClass;

import java.util.Date;

/**
 * ========== 主键策略配置表 ==========
 *
 * @author Mr.huang
 * @version KeyStrategyDO.java, v1.0
 * @date 2019年06月20日 22时46分
 */
@SelfTableClass("t_key_strategy")
public class KeyStrategyDO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 维度1-字段名
     */
    private String key1;
    /**
     * 维度1-字段值
     */
    private String value1;
    /**
     * 维度2-字段名
     */
    private String key2;
    /**
     * 维度2-字段值
     */
    private String value2;
    /**
     * 当前的值
     */
    private Integer idCurrentValue;
    /**
     * 每次增加的值
     */
    private Integer incrementValue;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public Integer getIdCurrentValue() {
        return idCurrentValue;
    }

    public void setIdCurrentValue(Integer idCurrentValue) {
        this.idCurrentValue = idCurrentValue;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

    public void setIncrementValue(Integer incrementValue) {
        this.incrementValue = incrementValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
