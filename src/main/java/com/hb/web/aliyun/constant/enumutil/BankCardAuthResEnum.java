package com.hb.web.aliyun.constant.enumutil;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== 银行卡实名认证响应枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.constant.enumutil.SMSResEnum.java, v1.0
 * @date 2019年08月12日 19时37分
 */
public enum BankCardAuthResEnum {

    success("01", "验证通过", "验证通过", "验证通过"),
    failed("02", "验证不通过", "验证不通过", "验证不通过"),
    cannot_auth("202", "无法验证", "无法验证", "无法验证"),
    auth_err("203", "异常情况", "异常情况(同一身份证号重复调用次数达到上限，请12小时后在请求)", "异常情况"),
    error_name("204", "姓名输入错误", "姓名输入错误", "姓名输入错误"),
    error_idcard("205", "身份证号输入错误", "身份证号输入错误", "身份证号输入错误"),
    error_bankcard("206", "银行卡号输入错误", "银行卡号输入错误", "银行卡号输入错误"),
    error("9999", "系统异常", "系统异常", "系统异常，请稍后再试。");

    private String code;
    private String message;
    private String desc;
    private String hint;

    BankCardAuthResEnum(String code, String message, String desc, String hint) {
        this.code = code;
        this.message = message;
        this.desc = desc;
        this.hint = hint;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }

    public String getHint() {
        return hint;
    }

    /**
     * ########## 根据code匹配 ##########
     *
     * @param code code
     * @return BankCardAuthResEnum
     */
    public static BankCardAuthResEnum match(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (BankCardAuthResEnum bankCardAuthResEnum : BankCardAuthResEnum.values()) {
            if (StringUtils.equals(bankCardAuthResEnum.code, code)) {
                return bankCardAuthResEnum;
            }
        }
        return null;
    }

}
