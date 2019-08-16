package com.hb.web.vo.appvo.request;

import java.io.Serializable;

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
    private String rechargeMoney;

    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    @Override
    public String toString() {
        return "RechargeRequestVO{" +
                ", rechargeMoney='" + rechargeMoney + '\'' +
                '}';
    }
}
