package com.hb.facade.vo.webvo.response;

import java.io.Serializable;
import java.math.BigDecimal;

public class BackRiskControlResponseVO implements Serializable {

    private static final long serialVersionUID = 4434185138728811147L;

    private String orderId;
    private BigDecimal profit;
    private BigDecimal currentPrice;

    public BackRiskControlResponseVO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "BackRiskControlResponseVO{" +
                "orderId='" + orderId + '\'' +
                ", profit=" + profit +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
