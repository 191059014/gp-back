package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 绑定银行卡 ==========
 *
 * @author Mr.huang
 * @version BankCardRequestVO.java, v1.0
 * @date 2019年06月16日 08时39分
 */
public class BankCardRequestVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8499579527544517207L;

    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 银行卡号
     */
    private String bankNo;
    /**
     * 支付密码
     */
    private String payPassword;
    /**
     * 开户行
     */
    private String accountBank;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    @Override
    public String toString() {
        return "BankCardRequestVO{" +
                "bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", accountBank='" + accountBank + '\'' +
                '}';
    }

}
