package com.hb.web.constant.enumutil;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== 订单状态 ==========
 *
 * @author Mr.huang
 * @version OrderStatusEnum.java, v1.0
 * @date 2019年06月22日 13时57分
 */
public enum OrderStatusEnum {

    IN_THE_POSITION("1", "持仓中"),
    DELEGATION("2", "委托中"),
    ALREADY_SETTLED("3", "已结算"),
    GIVEUP("4", "已放弃");

    /**
     * 值
     */
    private String value;
    /**
     * 描述
     */
    private String name;

    OrderStatusEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String stateOf(String value) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (StringUtils.equals(orderStatusEnum.value, value)) {
                return orderStatusEnum.name;
            }
        }
        return null;
    }

}
