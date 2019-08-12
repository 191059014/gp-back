package com.hb.web.aliyun.model;

import com.hb.web.aliyun.constant.enumutil.SMSResEnum;

/**
 * ========== 短信发送返回模型 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.SMSSendResult.java, v1.0
 * @date 2019年08月12日 19时38分
 */
public class SMSSendResult {

    private String mobile;

    private Integer verifyCode;

    private String code;

    private String message;

    public SMSSendResult(String mobile, Integer verifyCode, SMSResEnum smsResEnum) {
        this.mobile = mobile;
        this.verifyCode = verifyCode;
        this.code = smsResEnum.getCode();
        this.message = smsResEnum.getHint();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Integer verifyCode) {
        this.verifyCode = verifyCode;
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

    @Override
    public String toString() {
        return "SMSSendResult{" +
                "mobile='" + mobile + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
