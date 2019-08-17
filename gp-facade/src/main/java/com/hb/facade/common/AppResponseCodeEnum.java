package com.hb.facade.common;

/**
 * ========== app返回码枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.constant.enumutil.AppResponseCodeEnum.java, v1.0
 * @date 2019年06月13日 23时07分
 */
public enum AppResponseCodeEnum {

    // 成功
    SUCCESS(200, "成功"),
    // 失败
    FAIL(400, "失败"),
    // 网络故障
    ERROR_NETWORK(888, "网络故障"),
    // 参数校验失败
    ERROR_PARAM_VERIFY(998, "必要信息为空"),
    // 数据转换异常
    ERROR_CONVERT(999, "数据转换异常"),
    // 未知异常
    ERROR_UNKNOW(1000, "未知异常"),
    // 业务异常
    ERROR_APP(1001, "业务异常"),
    // Token失效
    ERROR_TOKEN(1002, "Token失效"),
    // 签名错误
    ERROR_SIGNATURE(1003, "签名错误"),
    // 账号在其他设备登陆
    ERROR_OTHER_PHONE_LOGIN(1004, "账号在其他设备登陆"),
    // 验证码不正确
    ERROR_VERIFY(1005, "验证码不正确"),
    // 版本过低
    ERROR_API_VERSION_TOO_LOW(1006, "版本过低"),
    // 用户名不正确
    ERROR_USERNAME(1007, "用户名不正确"),
    // 密码不正确
    ERROR_PASSWORD(1008, "密码不正确"),
    // 手机验证码不正确
    ERROR_MOBILE_VERIFYCODE(1009, "手机验证码不正确"),
    // 手机验证码无效
    INVALID_MOBILE_VERIFYCODE(1010, "验证码无效，请重新获取"),
    // 手机号已经注册过
    MOBILE_ALREADY_EXIST(1011, "手机号已经注册过"),
    // 手机号格式不正确
    ERROR_MOBILE_FORMAT(1012, "手机号格式不正确"),
    // 密码格式不正确
    ERROR_PASSWORD_FORMAT(1013, "密码格式不正确"),
    // 邀请人手机号错误
    ERROR_INVITER_MOBILE(1014, "邀请人手机号错误"),
    // 查询不到用户信息
    NOT_EXIST_USER(1015, "查询不到用户信息"),

    /**
     * 用户资金
     */
    // 查询不到用户的资金信息
    NO_FUND_INFO(2000, "查询不到用户的资金信息"),
    NOT_ENOUGH_MONEY(2001, "您的账户余额不足，请及时充值"),
    NOT_ENOUGH_USEABLE_MONEY(2002, "您的账户余额不足，无法提现"),
    NOT_IDCARD_REALNAME_AUTH(2003, "请先进行身份证实名认证");

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;

    AppResponseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
