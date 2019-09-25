package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ========== 用户资金分类汇总 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.response.UserFundSubTotalResponseVO.java, v1.0
 * @date 2019年07月20日 16时18分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFundSubTotalResponseVO implements Serializable {
    // serialVersionUID
    private static final long serialVersionUID = -4421345602768323767L;
    // 总资产
    private BigDecimal accountTotalMoney;
    // 可用余额
    private BigDecimal usableMoney;
    // 持仓总市值
    private BigDecimal totalStrategyMoney;
    // 持仓信用金
    private BigDecimal totalStrategyOwnMoney;
    // 浮动盈亏
    private BigDecimal totalProfitAndLossMoney;

    public BigDecimal getAccountTotalMoney() {
        return accountTotalMoney;
    }

    public void setAccountTotalMoney(BigDecimal accountTotalMoney) {
        this.accountTotalMoney = accountTotalMoney;
    }

    public BigDecimal getUsableMoney() {
        return usableMoney;
    }

    public void setUsableMoney(BigDecimal usableMoney) {
        this.usableMoney = usableMoney;
    }

    public BigDecimal getTotalStrategyMoney() {
        return totalStrategyMoney;
    }

    public void setTotalStrategyMoney(BigDecimal totalStrategyMoney) {
        this.totalStrategyMoney = totalStrategyMoney;
    }

    public BigDecimal getTotalStrategyOwnMoney() {
        return totalStrategyOwnMoney;
    }

    public void setTotalStrategyOwnMoney(BigDecimal totalStrategyOwnMoney) {
        this.totalStrategyOwnMoney = totalStrategyOwnMoney;
    }

    public BigDecimal getTotalProfitAndLossMoney() {
        return totalProfitAndLossMoney;
    }

    public void setTotalProfitAndLossMoney(BigDecimal totalProfitAndLossMoney) {
        this.totalProfitAndLossMoney = totalProfitAndLossMoney;
    }

    @Override
    public String toString() {
        return "UserFundSubTotalResponseVO{" +
                "accountTotalMoney=" + accountTotalMoney +
                "usableMoney=" + usableMoney +
                ", totalStrategyMoney=" + totalStrategyMoney +
                ", totalStrategyOwnMoney=" + totalStrategyOwnMoney +
                ", totalProfitAndLossMoney=" + totalProfitAndLossMoney +
                '}';
    }
}
