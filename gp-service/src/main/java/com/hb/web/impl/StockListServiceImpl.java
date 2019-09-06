package com.hb.web.impl;

import com.hb.facade.entity.StockListDO;
import com.hb.facade.enumutil.StockStateEnum;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.PageHelper;
import com.hb.web.api.IStockListService;
import com.hb.web.mapper.StockListMapper;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
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

    @Override
    public Integer findCount(StockListDO stockListDO) {
        return stockListMapper.findCount(stockListDO);
    }

    @Override
    public List<StockListDO> findStockList(StockListDO stockListDO, Integer pageNum, Integer pageSize) {
        return stockListMapper.findStockList(stockListDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public int addStock(StockListDO stockListDO) {
        return stockListMapper.insertSelective(stockListDO);
    }

    @Override
    public StockListDO findStock(StockListDO stockListDO) {
        return stockListMapper.findStock(stockListDO);
    }

    @Override
    public int updateStockById(StockListDO stockListDO) {
        return stockListMapper.updateByPrimaryKeySelective(stockListDO);
    }

    @Override
    public int deleteStockById(int id) {
        return stockListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map<String, Object>> getStockStatusList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (StockStateEnum stockStateEnum : StockStateEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", stockStateEnum.getValue());
            map.put("name", stockStateEnum.getDesc());
            resultList.add(map);
        }
        return resultList;
    }

}
