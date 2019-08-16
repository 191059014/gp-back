package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 热门股票vo ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.request.HotStockVO.java, v1.0
 * @date 2019年07月20日 12时49分
 */
public class HotStockVO implements Serializable {

    private String stockCode;
    private Long totalNum;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "HotStockVO{" +
                "stockCode='" + stockCode + '\'' +
                ", totalNum=" + totalNum +
                '}';
    }
}
