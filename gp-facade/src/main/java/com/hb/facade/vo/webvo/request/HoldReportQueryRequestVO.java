package com.hb.facade.vo.webvo.request;

import com.hb.facade.entity.UserDO;

import java.util.Date;

/**
 * 持仓中报表分页查询请求参数
 */
public class HoldReportQueryRequestVO extends UserDO {

    private static final long serialVersionUID = 708393165823717253L;

    /**
     * 下单时间起期
     */
    private Date orderTimeStart;
    /**
     * 下单时间止期
     */
    private Date orderTimeEnd;

    public Date getOrderTimeStart() {
        return orderTimeStart;
    }

    public void setOrderTimeStart(Date orderTimeStart) {
        this.orderTimeStart = orderTimeStart;
    }

    public Date getOrderTimeEnd() {
        return orderTimeEnd;
    }

    public void setOrderTimeEnd(Date orderTimeEnd) {
        this.orderTimeEnd = orderTimeEnd;
    }

    @Override
    public String toString() {
        return "HoldReportQueryRequestVO{" +
                "orderTimeStart=" + orderTimeStart +
                ", orderTimeEnd=" + orderTimeEnd +
                '}' + super.toString();
    }

}
