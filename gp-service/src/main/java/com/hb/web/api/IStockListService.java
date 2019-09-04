package com.hb.web.api;

import com.hb.facade.entity.StockListDO;

import java.util.Map;
import java.util.Set;

/**
 * ========== 股票相关service接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.remote.service.IStockListService.java, v1.0
 * @date 2019年05月31日 11时05分
 */
public interface IStockListService {


    Map<String, StockListDO> getStockMapBySet(Set<String> stockCodeSet);

}
