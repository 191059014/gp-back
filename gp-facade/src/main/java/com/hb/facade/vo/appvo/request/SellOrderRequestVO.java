package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 平仓 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.SellOrderRequestVO.java, v1.0
 * @date 2019年08月29日 00时00分
 */
public class SellOrderRequestVO implements Serializable {

    private static final long serialVersionUID = 3096409680488533938L;

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "SellOrderRequestVO{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
