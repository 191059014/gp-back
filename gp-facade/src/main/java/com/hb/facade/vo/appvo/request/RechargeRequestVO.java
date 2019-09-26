package com.hb.facade.vo.appvo.request;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ========== 充值 ==========
 *
 * @author Mr.huang
 * @version RechargeRequestVO.java, v1.0
 * @date 2019年06月16日 08时22分
 */
public class RechargeRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5042484560955390935L;

    /**
     * rechargeMoney
     */
    private BigDecimal rechargeMoney;

    private String payPassword;

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    public String toString() {
        return "RechargeRequestVO{" +
                ", rechargeMoney='" + rechargeMoney + '\'' +
                ", payPassword='" + payPassword + '\'' +
                '}';
    }
}
