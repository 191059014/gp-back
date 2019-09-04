package com.hb.web.impl;

import com.hb.facade.entity.StockListDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IStockListService;
import com.hb.web.mapper.StockListMapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 股票相关service实现类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.impl.StockServiceImpl.java, v1.0
 * @date 2019年05月31日 11时06分
 */
@Service
public class StockListServiceImpl implements IStockListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockListServiceImpl.class);

    private StockListMapper stockListMapper;

    @Override
    public Map<String, StockListDO> getStockMapBySet(Set<String> stockCodeSet) {
        if (CollectionUtils.isEmpty(stockCodeSet)) {
            return null;
        }
        List<StockListDO> list = stockListMapper.getStockListByStockCodeSet(stockCodeSet);
        Map<String, StockListDO> map = new HashedMap();
        for (StockListDO stock : list) {
            map.put(stock.getCode(), stock);
        }
        return map;
    }

}
