package com.hb.web.impl;

import com.hb.web.api.IStockListService;
import com.hb.web.mapper.StockListMapper;
import com.hb.web.model.StockListDO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ========== 股票信息查询 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.StockListServiceImpl.java, v1.0
 * @date 2019年08月13日 08时33分
 */
@Service
public class StockListServiceImpl implements IStockListService {

    @Autowired
    private StockListMapper stockListMapper;

    @Override
    public StockListDO getStockByCode(String stockCode) {
        Set<String> querys = new HashSet<>();
        querys.add(stockCode);
        List<StockListDO> list = stockListMapper.getStockListByStockCodeSet(querys);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public List<StockListDO> getStockListBySet(Set<String> stockCodeSet) {
        return stockListMapper.getStockListByStockCodeSet(stockCodeSet);
    }
}
