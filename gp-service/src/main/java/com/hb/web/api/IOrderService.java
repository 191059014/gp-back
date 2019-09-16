package com.hb.web.api;

import com.hb.facade.entity.OrderDO;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 订单操作service接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IOrderService.java, v1.0
 * @date 2019年06月25日 23时25分
 */
public interface IOrderService {

    int deleteByPrimaryKey(String orderId);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderDO record);

    List<OrderDO> findPageList(OrderDO orderDO, Integer pageNum, Integer pageSize);

    List<OrderDO> findAppListPages(OrderDO orderDO, Integer startRow, Integer pageSize);

    List<OrderDO> findByUserIdSetAndOrderStatus(Set<String> userIdSet, Set<Integer> orderStatuSet);

    List<OrderDO> findByUserIdSetAndOrderStatusAndTimeBetweenPages(Set<String> userIdSet, Set<Integer> orderStatuSet, Date timeBegin, Date timeEnd, Integer pageNum, Integer pageSize);

    int findByUserIdSetAndOrderStatusAndTimeBetweenPagesCount(Set<String> userIdSet, Set<Integer> orderStatuSet, Date timeBegin, Date timeEnd);

    Integer findCount(OrderDO orderDO);

    List<Map<String, Object>> getOrderStatusList();
}
