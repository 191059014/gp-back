package com.hb.facade.vo.appvo.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 追加信用金
 */
public class AppendOrderMoneyRequestVO implements Serializable {

    private static final long serialVersionUID = 1311236053147838403L;

    private String orderId;
    private BigDecimal appendMoney;

    public BigDecimal getAppendMoney() {
        return appendMoney;
    }

    public void setAppendMoney(BigDecimal appendMoney) {
        this.appendMoney = appendMoney;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "AppendOrderMoneyRequestVO{" +
                "orderId=" + orderId +
                ", appendMoney=" + appendMoney +
                '}';
    }
}
