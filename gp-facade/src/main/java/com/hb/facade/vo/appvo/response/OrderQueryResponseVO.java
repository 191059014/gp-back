package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.facade.entity.OrderDO;

import java.io.Serializable;
import java.util.List;

/**
 * ========== 订单查询响应信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.response.OrderQueryResponseVO.java, v1.0
 * @date 2019年07月06日 17时36分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderQueryResponseVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2857904505345173473L;
    /**
     * 订单列表
     */
    private List<QueryOrderPageResponseVO> orderList;

    public List<QueryOrderPageResponseVO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<QueryOrderPageResponseVO> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "OrderQueryResponseVO{" +
                "orderList=" + String.valueOf(orderList) +
                '}';
    }
}
