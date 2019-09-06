package com.hb.facade.enumutil;

public enum StockStateEnum {

    normal(1, "正常"),
    stop(2, "停牌"),
    high_risk(3, "高风险");

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
