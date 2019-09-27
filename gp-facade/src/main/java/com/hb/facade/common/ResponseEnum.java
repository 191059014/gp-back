package com.hb.facade.common;

/**
 * ========== 响应码枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.common.ResponseEnum.java, v1.0
 * @date 2019年06月05日 16时21分
 */
public enum ResponseEnum {

    UNKONW("00000", "不确定"),

    SUCCESS("10000", "操作成功"),
    ERROR_PARAM_VERIFY("99996", "必要参数为空"),
    REQUEST_IN_BLACKLIST("99997", "当前IP已纳入黑名单，禁止访问"),
    REQUEST_TOO_OFTEN("99998", "当前操作太过频繁，请稍后再试"),
    ERROR("99999", "操作失败，请稍后再试"),
    /**
     * 客户资金流水
     */
    UPDATE_CUSTOMER_FUND_DETAIL_FAILED("20000", "更新客户资金流水失败"),
    /**
     * 客户资金
     */
    ADD_CUSTOMER_FUND_FAILED("20050", "更新客户资金信息失败"),
    NO_FUND_INFO("20051", "查询不到用户的资金信息"),
    NOT_ENOUGH_USEABLE_MONEY("20052", "您的账户余额不足，无法提现"),

    /**
     * 登陆相关
     */
    LOGIN_SUCCESS("90100", "登录成功"),
    USERNAME_WRONG("90101", "用户名不正确"),
    PASSWORD_WRONG("90102", "密码不正确"),
    USERNAME_NOT_NULL("90103", "用户名不能为空"),
    PASSWORD_NOT_NULL("90104", "密码不能为空"),
    MOBILE_ALREADY_EXIST("90105", "手机号已经注册过"),
    OLD_PASSWORD_WRONG("90106", "原密码不正确"),
    PLEASE_REALNAME_AUTH("90107", "请先进行身份证实名认证"),
    REALNAME_AUTH_FAILED("90108", "身份证实名认证不通过"),
    BANKCARD_AUTH_FAILED("90109", "银行卡实名认证不通过"),
    PARAM_TYPE_ERROR("90110", "参数格式不正确"),
    MOBILE_ERROR("90111", "手机号格式错误"),
    NO_SESSION("90199", "缓存过期，请重新登陆"),
    PERMISSION_NOT_ENOUGH("90200", "权限不足");


    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
