package com.hb.facade.enumutil;

/**
 * ========== 资金类型 ==========
 *
 * @author Mr.huang
 * @version FundTypeEnum.java, v1.0
 * @date 2019年06月13日 16时14分
 */
public enum FundTypeEnum {

    DEFERRED_FEE(1, "递延费分成", "递延费扣除"),
    FREEZE(2, "冻结", "冻结信息服务费"),
    RECHARGE(3, "充值", "账户充值"),
    DEPOSIT(4, "提现", "账户充值");

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
