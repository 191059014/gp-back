package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 客户资金流水请求VO ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.request.QueryCustomerFundDetailRequestVO.java, v1.0
 * @date 2019年07月22日 21时01分
 */
public class QueryCustomerFundDetailRequestVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3879123820139303922L;
    /**
     * 资金类型
     */
    private Integer fundType;
    /**
     * 开始行数
     */
    private Integer startRow;
    /**
     * 每页条数
     */
    private Integer pageNum;

    public Integer getFundType() {
        return fundType;
    }

    public void setFundType(Integer fundType) {
        this.fundType = fundType;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "QueryCustomerFundDetailRequestVO{" +
                "fundType=" + fundType +
                ", startRow=" + startRow +
                ", pageNum=" + pageNum +
                '}';
    }
}
