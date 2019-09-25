package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.facade.entity.OrderDO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryOrderPageResponseVO extends OrderDO {

    private static final long serialVersionUID = -7217872689708178108L;

    private Integer stockState;

    public Integer getStockState() {
        return stockState;
    }

    public void setStockState(Integer stockState) {
        this.stockState = stockState;
    }

    @Override
    public String toString() {
        return "QueryOrderPageResponseVO{" +
                "stockState=" + stockState +
                '}' + super.toString();
    }
}
