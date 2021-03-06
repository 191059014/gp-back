package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * ========== 登陆成功响应vo ==========
 *
 * @author Mr.huang
 * @version LoginResponseVO.java, v1.0
 * @date 2019年06月22日 13时18分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseVO implements Serializable {

    private static final long serialVersionUID = -3100775369243847924L;
    /**
     * token
     */
    private String token;
    /**
     * secret
     */
    private String secret;

    public LoginResponseVO(String token, String secret) {
        this.token = token;
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "LoginResponseVO{" +
                "token='" + token + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
