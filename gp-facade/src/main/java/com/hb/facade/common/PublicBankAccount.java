package com.hb.facade.common;

import java.io.Serializable;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.common.PublicBankAccount.java, v1.0
 * @date 2019年09月25日 13时12分
 */
public class PublicBankAccount implements Serializable {

    private static final long serialVersionUID = -2413768000408789309L;

    private String bankName;
    private String bankNum;
    private String bankAddress;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    @Override
    public String toString() {
        return "PublicBankAccount{" +
                "bankName='" + bankName + '\'' +
                ", bankNum='" + bankNum + '\'' +
                ", bankAddress='" + bankAddress + '\'' +
                '}';
    }
}
