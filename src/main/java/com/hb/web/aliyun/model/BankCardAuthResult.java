package com.hb.web.aliyun.model;

import com.hb.web.aliyun.constant.enumutil.BankCardAuthResEnum;
import com.hb.web.aliyun.model.out.BankCardOutRes;

import java.io.Serializable;

/**
 * ========== 银行卡实名认证结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.BankCardAuthResult.java, v1.0
 * @date 2019年08月12日 21时58分
 */
public class BankCardAuthResult implements Serializable {

    private static final long serialVersionUID = -125137282431183922L;

    private String code;
    private String message;
    private BankCardOutRes result;

    public BankCardAuthResult(BankCardAuthResEnum bankCardAuthResEnum, BankCardOutRes result) {
        this.code = bankCardAuthResEnum.getCode();
        this.message = bankCardAuthResEnum.getHint();
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BankCardOutRes getResult() {
        return result;
    }

    public void setResult(BankCardOutRes result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BankCardAuthResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + String.valueOf(result) +
                '}';
    }
}
