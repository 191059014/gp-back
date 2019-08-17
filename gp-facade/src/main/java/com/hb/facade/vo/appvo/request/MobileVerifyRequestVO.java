package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 获取手机验证码请求信息 ==========
 *
 * @author Mr.huang
 * @version MobileVerifyRequestVO.java, v1.0
 * @date 2019年06月22日 13时23分
 */
public class MobileVerifyRequestVO implements Serializable {

    /**
     * 手机号
     */
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "MobileVerifyRequestVO{" +
                "mobile='" + mobile + '\'' +
                '}';
    }
}
