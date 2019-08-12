package com.hb.web.aliyun.model;

import com.hb.web.aliyun.constant.enumutil.IdCardAuthResEnum;
import com.hb.web.aliyun.model.out.IdCardOutRes;

import java.io.Serializable;

/**
 * ========== 身份证实名认证结果 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.model.IdCardAuthResult.java, v1.0
 * @date 2019年08月12日 22时26分
 */
public class IdCardAuthResult implements Serializable {

    private static final long serialVersionUID = -3874251278806524356L;

    private String code;
    private String message;
    private IdCardOutRes result;

    public IdCardAuthResult(IdCardAuthResEnum idCardAuthResEnum, IdCardOutRes result) {
        this.code = idCardAuthResEnum.getCode() + "";
        this.message = idCardAuthResEnum.getHint();
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

    public IdCardOutRes getResult() {
        return result;
    }

    public void setResult(IdCardOutRes result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "IdCardAuthResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + String.valueOf(result) +
                '}';
    }
}
