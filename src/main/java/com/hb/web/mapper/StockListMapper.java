package com.hb.web.mapper;

import com.hb.web.model.StockListDO;

import java.util.List;
import java.util.Set;

public interface StockListMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StockListDO record);

    int insertSelective(StockListDO record);

    StockListDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockListDO record);

    int updateByPrimaryKey(StockListDO record);

    List<StockListDO> getStockListByStockCodeSet(Set<String> stockCodeSet);

}