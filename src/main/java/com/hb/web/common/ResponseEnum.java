package com.hb.web.common;

/**
 * ========== 响应码枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.ResponseEnum.java, v1.0
 * @date 2019年06月05日 16时21分
 */
public enum ResponseEnum {

    UNKONW("00000", "不确定"),

    SUCCESS("10000", "操作成功"),
    ERROR("99999", "操作失败，请稍后再试"),

    /**
     * 登陆相关
     */
    USERNAME_WRONG("90101", "用户名不正确"),
    PASSWORD_WRONG("90102", "密码不正确"),
    USERNAME_NOT_NULL("90103", "用户名不能为空"),
    PASSWORD_NOT_NULL("90104", "密码不能为空"),
    MOBILE_ALREADY_EXIST("90105", "手机号已经注册过"),
    LOGIN_SUCCESS("90100", "登录成功"),
    NO_SESSION("90199", "缓存过期，请重新登陆");


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
