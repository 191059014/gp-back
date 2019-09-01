package com.hb.facade.enumutil;

/**
 * ========== 资金类型 ==========
 *
 * @author Mr.huang
 * @version FundTypeEnum.java, v1.0
 * @date 2019年06月13日 16时14分
 */
public enum FundTypeEnum {

    RECHARGE(1, "充值", "账户充值"),
    DEPOSIT(2, "提现", "账户提现"),
    FREEZE(3, "冻结", "持仓中冻结资金"),
    DELAY(4, "递延费", "递延费扣除"),
    DELAY_BACK(5, "递延费返还", "递延费返还"),
    SERVICE(6, "服务费", "服务费"),
    APPEND(7, "追加信用金", "追加订单持仓信用金");

    private Integer value;
    private String name;
    private String desc;

    FundTypeEnum(Integer value, String name, String desc) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
