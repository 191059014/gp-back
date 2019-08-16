package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 根据股票代码模糊搜索股票信息-请求参数 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.request.StockQueryPageRequestVO.java, v1.0
 * @date 2019年08月14日 00时51分
 */
public class StockQueryPageRequestVO implements Serializable {

    private static final long serialVersionUID = -1244937184626484440L;

    private String stockCode;
    private Integer startRow;
    private Integer pageSize;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
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
        return "StockQueryPageRequestVO{" +
                "stockCode='" + stockCode + '\'' +
                ", startRow=" + startRow +
                ", pageSize=" + pageSize +
                '}';
    }
}
