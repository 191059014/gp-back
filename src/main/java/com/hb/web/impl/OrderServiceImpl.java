package com.hb.web.impl;

import com.hb.web.api.IOrderService;
import com.hb.web.constant.enumutil.OrderStatusEnum;
import com.hb.web.mapper.OrderMapper;
import com.hb.web.model.OrderDO;
import com.hb.web.util.DateUtils;
import com.hb.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========== 订单操作service实现类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.OrderServiceImpl.java, v1.0
 * @date 2019年06月25日 23时26分
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int deleteByPrimaryKey(String orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insertSelective(OrderDO record) {
        record.setCreateTime(DateUtils.getCurrentDate());
        record.setUpdateTime(DateUtils.getCurrentDate());
        return orderMapper.insertSelective(record);
    }

    @Override
    public OrderDO selectByPrimaryKey(String orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(OrderDO record) {
        record.setUpdateTime(DateUtils.getCurrentDate());
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<OrderDO> findList(OrderDO orderDO, Integer pageNum, Integer pageSize) {
        return orderMapper.findList(orderDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(OrderDO orderDO) {
        return orderMapper.findCount(orderDO);
    }

    @Override
    public List<Map<String, Object>> getOrderStatusList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", orderStatusEnum.getValue());
            map.put("name", orderStatusEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public Set<String> getHotStockSet(int number) {
        return orderMapper.getHotStockSet(number);
    }

}
