package com.hb.facade.vo.appvo.request;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * ========== 股票查询请求vo ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.StockQueryRequestVO.java, v1.0
 * @date 2019年06月25日 12时02分
 */
public class StockQueryRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9147488336312494251L;
    /**
     * 股票代码集合
     */
    private Set<String> stockCodeSet;

    public Set<String> getStockCodeSet() {
        return stockCodeSet;
    }

    public void setStockCodeSet(Set<String> stockCodeSet) {
        this.stockCodeSet = stockCodeSet;
    }

    @Override
    public String toString() {
        return "StockQueryRequestVO{" +
                "stockCodeSet=" + stockCodeSet +
                '}';
    }
}
