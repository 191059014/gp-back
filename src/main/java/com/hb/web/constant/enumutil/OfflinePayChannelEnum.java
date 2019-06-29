package com.hb.web.constant.enumutil;

/**
 * ========== 线下支付渠道 ==========
 *
 * @author Mr.huang
 * @version OfflinePayChannelEnum.java, v1.0
 * @date 2019年06月12日 15时08分
 */
public enum OfflinePayChannelEnum {

    BANK_TRANSFER("0", "银行转账"),
    E_CURRENCY_PAY("1", "网银支付"),
    ALIPAY("2", "支付宝"),
    WECHAT("3", "微信");

    private String value;
    private String name;

    OfflinePayChannelEnum(String value, String name) {
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
