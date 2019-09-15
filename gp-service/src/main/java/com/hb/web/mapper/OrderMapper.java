package com.hb.web.mapper;

import com.hb.facade.entity.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface OrderMapper {

    int deleteByPrimaryKey(String orderId);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderDO record);

    List<OrderDO> findList(@Param("orderDO") OrderDO orderDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("orderDO") OrderDO orderDO);

    List<OrderDO> findByUserIdSetAndOrderStatus(@Param("userIdSet") Set<String> userIdSet, @Param("orderStatuSet") Set<Integer> orderStatuSet);

    List<OrderDO> findByUserIdSetAndOrderStatusAndTimeBetweenPages(@Param("userIdSet") Set<String> userIdSet, @Param("orderStatuSet") Set<Integer> orderStatuSet
            , @Param("timeBegin") Date timeBegin, @Param("timeEnd") Date timeEnd, @Param("startRow") Integer pageNum, @Param("pageSize") Integer pageSize);

    int findByUserIdSetAndOrderStatusAndTimeBetweenPagesCount(@Param("userIdSet") Set<String> userIdSet, @Param("orderStatuSet") Set<Integer> orderStatuSet
            , @Param("timeBegin") Date timeBegin, @Param("timeEnd") Date timeEnd);
}