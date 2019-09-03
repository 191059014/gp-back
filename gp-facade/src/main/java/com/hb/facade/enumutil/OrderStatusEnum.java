package com.hb.facade.enumutil;

/**
 * ========== 订单状态 ==========
 *
 * @author Mr.huang
 * @version OrderStatusEnum.java, v1.0
 * @date 2019年06月22日 13时57分
 */
public enum OrderStatusEnum {

    ENTRUST(0, "委托中"),
    IN_THE_POSITION(1, "持仓中"),
    ALREADY_SETTLED(2, "已结算"),
    GIVEUP(3, "已放弃");

    /**
     * 值
     */
    private Integer value;
    /**
     * 描述
     */
    private String name;

    OrderStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String stateOf(String value) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.value.equals(value)) {
                return orderStatusEnum.name;
            }
        }
        return null;
    }

}
