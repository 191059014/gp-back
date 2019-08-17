package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 线下支付审核请求VO ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.QueryOfflinePayCheckRequestVO.java, v1.0
 * @date 2019年06月26日 22时22分
 */
public class QueryOfflinePayCheckRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6059643548642831615L;

    /**
     * 支付渠道
     */
    private Integer payChannel;

    /**
     * 审核状态
     */
    private Integer checkStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    /**
     * 开始行数
     */
    private Integer startRow;

    /**
     * 每页条数
     */
    private Integer pageSize;

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "QueryOfflinePayCheckRequestVO{" +
                "payChannel='" + payChannel + '\'' +
                ", checkStatus='" + checkStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }
}
