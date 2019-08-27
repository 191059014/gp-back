package com.hb.web.impl;

import com.hb.web.mapper.OrderMapper;
import com.hb.facade.entity.OrderDO;
import com.hb.facade.enumutil.OrderStatusEnum;
import com.hb.facade.enumutil.TableEnum;
import com.hb.facade.vo.appvo.request.HotStockVO;
import com.hb.unic.util.helper.PageHelper;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.api.IOrderService;
import com.hb.web.tool.KeyGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private KeyGenerator keyGenerator;

    @Override
    public int deleteByPrimaryKey(String orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insertSelective(OrderDO record) {
        record.setCreateTime(DateUtils.getCurrentDate());
        record.setUpdateTime(DateUtils.getCurrentDate());
        record.setOrderId(keyGenerator.generateKey(TableEnum.T_AGENT));
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
    public List<OrderDO> findAppPageList(OrderDO orderDO, Integer pageNum, Integer pageSize) {
        return orderMapper.findList(orderDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public List<OrderDO> findListPages(OrderDO orderDO, Integer startRow, Integer pageSize) {
        return orderMapper.findList(orderDO, startRow, pageSize);
    }

    @Override
    public List<OrderDO> findByUserIdAndOrderStatus(String userId, Set<Integer> orderStatuSet) {
        if (StringUtils.isBlank(userId) || CollectionUtils.isEmpty(orderStatuSet)) {
            return null;
        }
        return orderMapper.findByUserIdAndOrderStatus(userId, orderStatuSet);
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

}