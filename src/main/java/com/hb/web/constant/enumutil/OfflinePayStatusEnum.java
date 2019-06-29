package com.hb.web.constant.enumutil;

/**
 * ========== 线下支付-支付状态 ==========
 *
 * @author Mr.huang
 * @version OfflinePayChannelEnum.java, v1.0
 * @date 2019年06月12日 15时08分
 */
public enum OfflinePayStatusEnum {

    NOT_PAY("0", "未支付"),
    ALREADY_PAY("1", "已支付");

    private String value;
    private String name;

    OfflinePayStatusEnum(String value, String name) {
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
