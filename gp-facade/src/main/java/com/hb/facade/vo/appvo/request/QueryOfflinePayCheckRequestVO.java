package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 线下支付审核请求VO ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.QueryOfflinePayCheckRequestVO.java, v1.0
 * @date 2019年06月26日 22时22分
 */
public class QueryOfflinePayCheckRequestVO extends AppPages {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6059643548642831615L;

    /**
     * 资金类型
     */
    private Integer fundType;

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    @Override
    public String toString() {
        return "QueryOfflinePayCheckRequestVO{" +
                "fundType=" + fundType +
                "} " + super.toString();
    }
}