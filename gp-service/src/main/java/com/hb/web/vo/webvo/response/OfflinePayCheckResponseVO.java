package com.hb.web.vo.webvo.response;

import com.hb.facade.entity.OfflinePayChekDO;

/**
 * ========== 审核信息-请求vo ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.webvo.response.OfflinePayCheckResponseVO.java, v1.0
 * @date 2019年06月24日 23时30分
 */
public class OfflinePayCheckResponseVO extends OfflinePayChekDO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户手机号
     */
    private String mobile;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "OfflinePayCheckResponseVO{" +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
