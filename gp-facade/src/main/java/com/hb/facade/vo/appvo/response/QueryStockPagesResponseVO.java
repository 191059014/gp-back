package com.hb.facade.vo.appvo.response;

import com.hb.facade.entity.StockListDO;

import java.io.Serializable;
import java.util.List;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.response.QueryStockPagesResponseVO.java, v1.0
 * @date 2019年09月03日 17时31分
 */
public class QueryStockPagesResponseVO implements Serializable {

    private static final long serialVersionUID = -2154017487825335961L;

    private List<StockListDO> stockSearchList;

    public List<StockListDO> getStockSearchList() {
        return stockSearchList;
    }

    public void setStockSearchList(List<StockListDO> stockSearchList) {
        this.stockSearchList = stockSearchList;
    }

    @Override
    public String toString() {
        return "QueryStockPagesResponseVO{" +
                "stockSearchList=" + stockSearchList +
                '}';
    }
}
