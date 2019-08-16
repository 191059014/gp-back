package com.hb.web.vo.appvo.request;

import com.hb.facade.entity.CustomerFundDetailDO;

import java.io.Serializable;
import java.util.List;

/**
 * ========== 客户资金流水xia响应VO ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.appvo.request.QueryCustomerFundDetailResponseVO.java, v1.0
 * @date 2019年07月22日 21时03分
 */
public class QueryCustomerFundDetailResponseVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4620123913856291046L;

    private List<CustomerFundDetailDO> fundDetailList;

    public List<CustomerFundDetailDO> getFundDetailList() {
        return fundDetailList;
    }

    public void setFundDetailList(List<CustomerFundDetailDO> fundDetailList) {
        this.fundDetailList = fundDetailList;
    }

    @Override
    public String toString() {
        return "QueryCustomerFundDetailResponseVO{" +
                "fundDetailList=" + fundDetailList +
                '}';
    }
}
