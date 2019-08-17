package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 实名认证请求参数 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.IdCardRealNameAuthRequestVO.java, v1.0
 * @date 2019年08月13日 23时39分
 */
public class BankCardRealNameAuthRequestVO implements Serializable {

    private static final long serialVersionUID = -9107520801278115201L;

    private String bankNo;

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    @Override
    public String toString() {
        return "BankCardRealNameAuthRequestVO{" +
                "bankNo='" + bankNo + '\'' +
                '}';
    }
}
