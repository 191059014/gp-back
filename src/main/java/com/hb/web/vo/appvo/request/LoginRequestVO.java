package com.hb.web.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 登陆-请求实体 ==========
 *
 * @author Mr.huang
 * @version LoginRequestVO.java, v1.0
 * @date 2019年06月16日 17时03分
 */
public class LoginRequestVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3026682235965591996L;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequestVO{" +
                "mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
