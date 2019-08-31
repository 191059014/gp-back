package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * 递延请求vo
 */
public class DelayRequestVO implements Serializable {

    private static final long serialVersionUID = 5928379604612776428L;

    private String orderId;
    private Integer delayDays;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    @Override
    public String toString() {
        return "DelayRequestVO{" +
                "orderId='" + orderId + '\'' +
                ", delayDays=" + delayDays +
                '}';
    }
}
