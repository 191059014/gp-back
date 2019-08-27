package com.hb.web.api;

import com.hb.facade.entity.OrderDO;

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

    List<OrderDO> findAppPageList(OrderDO orderDO, Integer pageNum, Integer pageSize);

    List<OrderDO> findListPages(OrderDO orderDO, Integer startRow, Integer pageSize);

    List<OrderDO> findByUserIdAndOrderStatus(String userId, Set<Integer> orderStatuSet);

    Integer findCount(OrderDO orderDO);

    List<Map<String, Object>> getOrderStatusList();
}