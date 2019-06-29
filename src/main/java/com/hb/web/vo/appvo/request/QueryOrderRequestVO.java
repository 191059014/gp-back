package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 查询订单请求vo ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.request.QueryOrderRequestVO.java, v1.0
 * @date 2019年06月26日 22时12分
 */
public class QueryOrderRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5819275565790428353L;
    /**
     * 订单状态
     */
    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "QueryOrderRequestVO{" +
                "orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
