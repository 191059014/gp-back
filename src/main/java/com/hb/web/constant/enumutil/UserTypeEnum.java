package com.hb.web.constant.enumutil;

/**
 * ========== 用户类型 ==========
 *
 * @author Mr.huang
 * @version UserTypeEnum.java, v1.0
 * @date 2019年06月15日 22时04分
 */
public enum UserTypeEnum {

    USER("0", "客户"),
    AGENT("1", "代理商");

    /**
     * 值
     */
    private String value;
    /**
     * 描述
     */
    private String desc;

    UserTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
