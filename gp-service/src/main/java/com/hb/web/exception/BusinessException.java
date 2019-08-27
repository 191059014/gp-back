package com.hb.web.exception;

import com.hb.facade.common.ResponseEnum;

/**
 * ========== 自定义业务异常 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.exception.BusinessException.java, v1.0
 * @date 2019年08月27日 21时07分
 */
public class BusinessException extends RuntimeException {

    private String code;
    private String msg;

    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
