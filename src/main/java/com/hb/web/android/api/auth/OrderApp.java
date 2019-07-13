package com.hb.web.android.api.auth;

import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IOrderService;
import com.hb.web.constant.enumutil.OrderStatusEnum;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.model.OrderDO;
import com.hb.web.model.UserDO;
import com.hb.web.util.CloneUtils;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.appvo.request.OrderRequestVO;
import com.hb.web.vo.appvo.request.QueryOrderRequestVO;
import com.hb.web.vo.appvo.response.OrderQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ========== 订单 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.auth.OrderApp.java, v1.0
 * @date 2019年06月26日 10时30分
 */
@Api(tags = "[APP]订单")
@RestController
@RequestMapping("app/auth/order")
public class OrderApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderApp.class);

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation(value = "股票下单")
    @PostMapping("/order")
    public AppResultModel order(@RequestBody OrderRequestVO orderRequestVO) {
        LOGGER.info(LogUtils.appLog("股票下单，入参：{}"), orderRequestVO);
        UserDO userCache = getUserCache();
        OrderDO clone = CloneUtils.clone(orderRequestVO, OrderDO.class);
        clone.setUserId(userCache.getUserId());
        clone.setUserName(userCache.getUserName());
        clone.setOrderStatus(OrderStatusEnum.IN_THE_POSITION.getValue());
        clone.setUnit(userCache.getUnit());
        int i = iOrderService.insertSelective(clone);
        if (i < 1) {
            LOGGER.info(LogUtils.appLog("股票下单失败"));
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        LOGGER.info(LogUtils.appLog("股票下单成功"));
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS);
    }

    @ApiOperation(value = "查询订单")
    @PostMapping("/queryOrder")
    public AppResultModel<OrderQueryResponseVO> queryOrder(@RequestBody QueryOrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("查询订单，入参：{}"), requestVO);
        OrderQueryResponseVO responseVO = new OrderQueryResponseVO();
        OrderDO orderDO = new OrderDO();
        orderDO.setOrderStatus(requestVO.getOrderStatus());
        List<OrderDO> list = iOrderService.findListPages(orderDO, requestVO.getStartRow(), requestVO.getPageNum());
        responseVO.setOrderList(list);
        LOGGER.info(LogUtils.appLog("查询订单，返回结果：{}"), list);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}
