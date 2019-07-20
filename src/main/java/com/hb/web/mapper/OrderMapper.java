package com.hb.web.mapper;

import com.hb.web.model.OrderDO;
import com.hb.web.vo.appvo.HotStockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    int deleteByPrimaryKey(String orderId);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderDO record);

    List<OrderDO> findList(@Param("orderDO") OrderDO orderDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("orderDO") OrderDO orderDO);

    /**
     * ########## 获取热门股票 ##########
     *
     * @return 股票代码集合
     */
    List<HotStockVO> getHotStockList();
}