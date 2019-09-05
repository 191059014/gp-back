package com.hb.facade.enumutil;

public enum StockStateEnum {

    stop(1, "停牌");

    private Integer value;
    private String desc;

    StockStateEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 是否停牌股票
     *
     * @param state 状态
     * @return 是否停牌
     */
    public static boolean isStop(Integer state) {
        if (stop.value.equals(state)) {
            return true;
        }
        return false;
    }

}
