package com.hb.facade.vo.webvo.response;

import com.hb.facade.entity.OrderDO;

import java.math.BigDecimal;

public class AlreadySettledResponseVO extends OrderDO {

    private static final long serialVersionUID = 7723672309552320575L;

    /**
     * 净利润
     */
    private BigDecimal netProfit;

    public BigDecimal getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(BigDecimal netProfit) {
        this.netProfit = netProfit;
    }

    @Override
    public String toString() {
        return "AlreadySettledResponseVO{" +
                "netProfit=" + netProfit +
                '}' + super.toString();
    }
}
