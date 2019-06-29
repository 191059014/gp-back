package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 注册信息 ==========
 *
 * @author Mr.huang
 * @version RegisterRequestVO.java, v1.0
 * @date 2019年06月16日 07时53分
 */
public class RegisterRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4853590363465065789L;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 手机验证码
     */
    private String mobileVerifyCode;

    /**
     * 邀请人手机号码
     */
    private String inviterMobile;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileVerifyCode() {
        return mobileVerifyCode;
    }

    public void setMobileVerifyCode(String mobileVerifyCode) {
        this.mobileVerifyCode = mobileVerifyCode;
    }

    public String getInviterMobile() {
        return inviterMobile;
    }

    public void setInviterMobile(String inviterMobile) {
        this.inviterMobile = inviterMobile;
    }

    @Override
    public String toString() {
        return "RegisterRequestVO{" +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", mobileVerifyCode='" + mobileVerifyCode + '\'' +
                ", inviterMobile='" + inviterMobile + '\'' +
                '}';
    }
}
