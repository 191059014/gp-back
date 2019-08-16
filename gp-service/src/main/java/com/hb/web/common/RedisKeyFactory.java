package com.hb.web.common;

/**
 * ========== 缓存key统一管理 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.common.RedisKeyFactory.java, v1.0
 * @date 2019年08月13日 23时31分
 */
public class RedisKeyFactory {

    /**
     * ########## 手机验证码的key ##########
     *
     * @param mobile 手机号
     * @return 缓存key
     */
    public static String getMobileVerifyKey(String mobile) {
        return new StringBuilder("mobile_verifycode_").append(mobile).toString();
    }

}
