package com.hb.facade.enumutil;

/**
 * ========== 线下支付审核状态 ==========
 *
 * @author Mr.huang
 * @version OfflineCheckStatusEnum.java, v1.0
 * @date 2019年06月12日 15时07分
 */
public enum OfflineCheckStatusEnum {

    AUDITING(1, "待审核"),
    PASS(2, "通过"),
    REJECT(3, "拒绝");

    private Integer value;
    private String name;

    OfflineCheckStatusEnum(Integer value, String name) {
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
