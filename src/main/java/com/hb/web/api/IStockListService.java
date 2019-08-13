package com.hb.web.api;

import com.hb.web.model.StockListDO;

import java.util.List;
import java.util.Set;

/**
 * ========== 股票信息查询 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IStockListService.java, v1.0
 * @date 2019年08月13日 08时31分
 */
public interface IStockListService {

    StockListDO getStockByCode(String stockCode);

    List<StockListDO> getStockListBySet(Set<String> stockCodeSet);

}
