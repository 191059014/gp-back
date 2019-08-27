package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 客户资金流水请求VO ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.QueryCustomerFundDetailRequestVO.java, v1.0
 * @date 2019年07月22日 21时01分
 */
public class QueryCustomerFundDetailRequestVO extends AppPages {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3879123820139303922L;
    /**
     * 资金类型
     */
    private Integer fundType;
    private Long timeBegin;
    private Long timeEnd;

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public Long getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Long timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "QueryCustomerFundDetailRequestVO{" +
                "fundType=" + fundType +
                "timeBegin=" + timeBegin +
                "timeEnd=" + timeEnd +
                "} " + super.toString();
    }
}
