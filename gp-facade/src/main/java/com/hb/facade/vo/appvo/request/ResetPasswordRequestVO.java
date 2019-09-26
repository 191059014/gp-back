package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 重置密码 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.ResetPasswordRequestVO.java, v1.0
 * @date 2019年08月27日 09时13分
 */
public class ResetPasswordRequestVO implements Serializable {

    private static final long serialVersionUID = -4974130740409369911L;

    private String phoneNum;
    private String verify;
    private String password;
    private String payPassword;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequestVO{" +
                "phoneNum='" + phoneNum + '\'' +
                ", verify='" + verify + '\'' +
                ", password='" + password + '\'' +
                ", payPassword='" + payPassword + '\'' +
                '}';
    }
}
