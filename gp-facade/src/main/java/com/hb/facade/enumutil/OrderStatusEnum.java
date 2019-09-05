package com.hb.facade.enumutil;

/**
 * ========== 订单状态 ==========
 *
 * @author Mr.huang
 * @version OrderStatusEnum.java, v1.0
 * @date 2019年06月22日 13时57分
 */
public enum OrderStatusEnum {

    BUY_ENTRUST(10, "买入委托中"),
    IN_THE_POSITION(20, "持仓中"),
    SELL_ENTRUST(30, "卖出委托中"),
    ALREADY_SELL(50, "已卖出"),
    GIVEUP(40, "已放弃"),
    CALCLE(60, "已取消");

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
