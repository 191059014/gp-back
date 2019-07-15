package com.hb.web.constant.enumutil;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.constant.enumutil.TableEnum.java, v1.0
 * @date 2019年07月15日 13时38分
 */
public enum TableEnum {

    T_AGENT("t_agent", "agentId", "A");

    private String tableName;
    private String idName;
    private String keyPrefix;

    private TableEnum(String tableName, String idName, String keyPrefix) {
        this.tableName = tableName;
        this.idName = idName;
        this.keyPrefix = keyPrefix;
    }

    public String getTableName() {
        return tableName;
    }

    public String getIdName() {
        return idName;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

}
