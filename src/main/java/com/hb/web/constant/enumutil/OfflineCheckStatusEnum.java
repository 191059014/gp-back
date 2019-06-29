package com.hb.web.constant.enumutil;

/**
 * ========== 线下支付审核状态 ==========
 *
 * @author Mr.huang
 * @version OfflineCheckStatusEnum.java, v1.0
 * @date 2019年06月12日 15时07分
 */
public enum OfflineCheckStatusEnum {

    AUDITING("0", "待审核"),
    PASS("1", "通过"),
    REJECT("2", "拒绝");

    private String value;
    private String name;

    OfflineCheckStatusEnum(String value, String name) {
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
