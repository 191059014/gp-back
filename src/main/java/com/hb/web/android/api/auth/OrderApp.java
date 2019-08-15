package com.hb.web.android.api.auth;

import com.hb.entity.UserDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.CloneUtils;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.ICustomerFundService;
import com.hb.web.api.IOrderService;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.constant.enumutil.OrderStatusEnum;
import com.hb.entity.CustomerFundDO;
import com.hb.entity.OrderDO;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.appvo.request.OrderRequestVO;
import com.hb.web.vo.appvo.request.QueryOrderRequestVO;
import com.hb.web.vo.appvo.response.OrderQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @Autowired
    private ICustomerFundService iCustomerFundService;

    @ApiOperation(value = "股票下单")
    @PostMapping("/order")
    public AppResultModel order(@RequestBody OrderRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("股票下单，入参：{}"), requestVO);
        UserDO userCache = getUserCache();
        CustomerFundDO query = new CustomerFundDO(userCache.getUserId());
        CustomerFundDO customerFund = iCustomerFundService.findCustomerFund(query);
        BigDecimal strategyOwnMoney = requestVO.getStrategyOwnMoney();
        BigDecimal usableMoney = customerFund.getUsableMoney();
        if (strategyOwnMoney.compareTo(usableMoney) > 0) {
            // 余额不足
            return AppResultModel.generateResponseData(AppResponseCodeEnum.NOT_ENOUGH_MONEY);
        }
        OrderDO clone = CloneUtils.clone(requestVO, OrderDO.class);
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

        /**
         * 更新账户信息
         *
         * 1.总金额不变
         * 2.冻结金额增加
         * 3.可用余额减少
         */
        CustomerFundDO updateCustomerFund = new CustomerFundDO(customerFund.getUserId());
        BigDecimal newFreezeMoney = BigDecimalUtils.add(customerFund.getFreezeMoney(), requestVO.getStrategyOwnMoney());
        BigDecimal newUsableMoney = BigDecimalUtils.subtract(customerFund.getUsableMoney(), requestVO.getStrategyOwnMoney());
        updateCustomerFund.setFreezeMoney(newFreezeMoney);
        updateCustomerFund.setUsableMoney(newUsableMoney);
        iCustomerFundService.updateByPrimaryKeySelective(updateCustomerFund);
        LOGGER.info(LogUtils.appLog("股票下单成功后更新账户信息成功"));

        alarmTools.alert("APP", "订单", "下单接口", "用户【" + userCache.getUserName() + "】下单成功，订单号：" + clone.getOrderId());
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
