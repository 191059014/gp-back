package com.hb.web.mapper;

import com.hb.entity.OrderDO;
import com.hb.web.vo.appvo.HotStockVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    List<OrderDO> findByUserIdAndOrderStatus(@Param("userId") String userId, @Param("orderStatuSet") Set<Integer> orderStatuSet);
}