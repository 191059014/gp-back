package com.hb.web.vo.appvo.response;

import com.hb.entity.OrderDO;

import java.io.Serializable;
import java.util.List;

/**
 * ========== 订单查询响应信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.response.OrderQueryResponseVO.java, v1.0
 * @date 2019年07月06日 17时36分
 */
public class OrderQueryResponseVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2857904505345173473L;
    /**
     * 订单列表
     */
    private List<OrderDO> orderList;

    public List<OrderDO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDO> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "OrderQueryResponseVO{" +
                "orderList=" + String.valueOf(orderList) +
                '}';
    }
}
