package com.hb.facade.vo.appvo.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ========== 提现请求数据 ==========
 *
 * @author Mr.huang
 * @version DepositRequestVO.java, v1.0
 * @date 2019年06月24日 10时22分
 */
public class DepositRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6018127208516971048L;

    private BigDecimal depositMoney;

    public BigDecimal getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(BigDecimal depositMoney) {
        this.depositMoney = depositMoney;
    }

    @Override
    public String toString() {
        return "DepositRequestVO{" +
                "depositMoney=" + depositMoney +
                '}';
    }
}
