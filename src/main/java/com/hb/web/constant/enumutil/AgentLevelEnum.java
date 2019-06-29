package com.hb.web.constant.enumutil;

/**
 * ========== 代理商类型 ==========
 *
 * @author Mr.huang
 * @version AgentLevelEnum.java, v1.0
 * @date 2019年06月13日 11时53分
 */
public enum AgentLevelEnum {

    FIRST("1", "一级代理商"),
    SECOND("2", "二级代理商");

    private String value;
    private String name;

    AgentLevelEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
