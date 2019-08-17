package com.hb.facade.vo.appvo.response;

import java.io.Serializable;

/**
 * ========== 实名认证响应参数 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.response.BankCardRealNameAuthResponseVO.java, v1.0
 * @date 2019年08月14日 00时06分
 */
public class BankCardRealNameAuthResponseVO implements Serializable {

    private static final long serialVersionUID = -6026825235609570687L;

    private String bankName;

    public BankCardRealNameAuthResponseVO(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "BankCardRealNameAuthResponseVO{" +
                "bankName='" + bankName + '\'' +
                '}';
    }
}
