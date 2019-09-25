package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 排行榜信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rank implements Serializable {

    private static final long serialVersionUID = 6789833457642451973L;

    private String userName;
    // 累计盈亏
    private BigDecimal totalProfitAndLossMoney;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalProfitAndLossMoney() {
        return totalProfitAndLossMoney;
    }

    public void setTotalProfitAndLossMoney(BigDecimal totalProfitAndLossMoney) {
        this.totalProfitAndLossMoney = totalProfitAndLossMoney;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "userName='" + userName + '\'' +
                ", totalProfitAndLossMoney=" + totalProfitAndLossMoney +
                '}';
    }

}
