package com.hb.web.constant.enumutil;

/**
 * ========== 实名认证状态 ==========
 *
 * @author Mr.huang
 * @version RealAuthStatusEnum.java, v1.0
 * @date 2019年06月13日 11时56分
 */
public enum RealAuthStatusEnum {

    NO_AUTH(0, "未实名认证"),
    IS_AUTH(1, "已实名认证");

    private Integer value;
    private String name;

    RealAuthStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
