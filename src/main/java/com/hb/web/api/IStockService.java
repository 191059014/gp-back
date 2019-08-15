package com.hb.web.api;

import com.hb.entity.StockListDO;
import com.hb.web.vo.StockIndexModel;
import com.hb.web.vo.StockModel;

import java.util.List;
import java.util.Set;

/**
 * ========== 股票相关service接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IStockService.java, v1.0
 * @date 2019年05月31日 11时05分
 */
public interface IStockService {

    /**
     * ###### 根据股票代码查询股票信息 ######
     *
     * @param stockCodeSet 股票代码集合
     * @return 股票信息
     */
    List<StockModel> queryStockList(Set<String> stockCodeSet);

    /**
     * ###### 根据指数代码查询指数信息 ######
     *
     * @param indexCodeSet 指数代码集合
     * @return 指数信息
     */
    List<StockIndexModel> queryStockIndexList(Set<String> indexCodeSet);

    StockListDO getStockByCode(String stockCode);

    List<StockListDO> getStockListBySet(Set<String> stockCodeSet);

    List<StockListDO> findPageList(String stockCode, Integer startRow, Integer pageSize);
}
