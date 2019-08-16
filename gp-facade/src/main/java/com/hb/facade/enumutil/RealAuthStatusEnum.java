package com.hb.facade.enumutil;

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

    public static String stateOf(Integer value) {
        if (value == null) {
            return null;
        }
        for (RealAuthStatusEnum realAuthStatusEnum : RealAuthStatusEnum.values()) {
            if (realAuthStatusEnum.value.compareTo(value) == 0) {
                return realAuthStatusEnum.getName();
            }
        }
        return null;
    }

}
