package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 查询订单请求vo ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.QueryOrderRequestVO.java, v1.0
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
    private Integer orderStatus;
    /**
     * 开始行数
     */
    private Integer startRow;
    /**
     * 每页条数
     */
    private Integer pageNum;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "QueryOrderRequestVO{" +
                "orderStatus='" + orderStatus + '\'' +
                ", startRow=" + startRow +
                ", pageNum=" + pageNum +
                '}';
    }
}
