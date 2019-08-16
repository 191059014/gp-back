package com.hb.facade.enumutil;

/**
 * ========== 主键生成策略枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.enumutil.TableEnum.java, v1.0
 * @date 2019年07月15日 13时38分
 */
public enum TableEnum {

    T_AGENT("t_order", "orderId", "OD", "t_agent_sequence");

    private String tableName;
    private String idName;
    private String keyPrefix;
    private String sequenceKey;

    private TableEnum(String tableName, String idName, String keyPrefix, String sequenceKey) {
        this.tableName = tableName;
        this.idName = idName;
        this.keyPrefix = keyPrefix;
        this.sequenceKey = sequenceKey;
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

    public String getSequenceKey() {
        return sequenceKey;
    }
}
