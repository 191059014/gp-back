package com.hb.facade.vo.webvo.response;

import com.hb.facade.entity.OrderDO;

/**
 * ========== 订单查询响应VO ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.webvo.response.OrderQueryResponseVO.java, v1.0
 * @date 2019年06月26日 20时45分
 */
public class OrderQueryResponseVO extends OrderDO {

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "OrderQueryResponseVO{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
