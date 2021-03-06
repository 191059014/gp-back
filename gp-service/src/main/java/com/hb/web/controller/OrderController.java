package com.hb.web.controller;

import com.hb.facade.entity.OrderDO;
import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.OrderStatusEnum;
import com.hb.unic.util.util.CloneUtils;
import com.hb.web.api.IOrderService;
import com.hb.web.api.IUserService;
import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.vo.webvo.response.OrderQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ========== 订单controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.OrderController.java, v1.0
 * @date 2019年06月25日 23时31分
 */
@RestController
@RequestMapping("controller/order")
@Api(tags = "[WEB]订单")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "分页条件查询订单列表")
    @PostMapping("/getOrderListPage")
    public ResponseData<List<OrderQueryResponseVO>> getOrderListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody OrderDO orderDO) {
        List<OrderQueryResponseVO> resultList = new ArrayList<>();
        orderDO.setOrderStatus(OrderStatusEnum.IN_THE_POSITION.getValue());
        List<OrderDO> orderList = iOrderService.findPageList(orderDO, pageNum, pageSize);
        Integer count = iOrderService.findCount(orderDO);
        if (CollectionUtils.isNotEmpty(orderList)) {
            Set<String> userIdSet = orderList.stream().map(OrderDO::getUserId).collect(Collectors.toSet());
            Map<String, UserDO> userMap = iUserService.getUserMapByUserIdSet(userIdSet);
            for (OrderDO order : orderList) {
                OrderQueryResponseVO clone = CloneUtils.clone(order, OrderQueryResponseVO.class);
                clone.setUserName(userMap.get(order.getUserId()) == null ? "" : userMap.get(order.getUserId()).getUserName());
                clone.setMobile(userMap.get(order.getUserId()) == null ? "" : userMap.get(order.getUserId()).getMobile());
                resultList.add(clone);
            }
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, resultList, count);
    }

    @ApiOperation(value = "添加订单")
    @PostMapping("/addOrder")
    public ResponseData addOrder(@RequestBody OrderDO orderDO) {
        int result = iOrderService.insertSelective(orderDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询订单")
    @PostMapping("/findOrderByKey")
    public ResponseData findOrderByKey(String orderId) {
        OrderDO result = iOrderService.selectByPrimaryKey(orderId);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改订单")
    @PostMapping("/updateOrder")
    public ResponseData updateOrder(@RequestBody OrderDO orderDO) {
        int result = iOrderService.updateByPrimaryKeySelective(orderDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除订单")
    @GetMapping("/deleteOrderById")
    public ResponseData deleteOrderById(String orderId) {
        int result = iOrderService.deleteByPrimaryKey(orderId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "获取订单状态下拉框")
    @GetMapping("/getOrderStatusCombobox")
    public ResponseData<List<Map<String, Object>>> getOrderStatusCombobox() {
        List<Map<String, Object>> result = iOrderService.getOrderStatusList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

}
