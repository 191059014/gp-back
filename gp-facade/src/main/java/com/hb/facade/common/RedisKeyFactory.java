package com.hb.facade.common;

/**
 * ========== 缓存key统一管理 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.common.RedisKeyFactory.java, v1.0
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

    /**
     * 获取用户信息缓存key
     *
     * @param userId 用户ID
     * @return key
     */
    public static String getUserCacheKey(String userId) {
        return new StringBuilder("user_info_").append(userId).toString();
    }

    /**
     * 获取代理商信息缓存key
     *
     * @param mobile 代理商手机号
     * @return key
     */
    public static String getAgentCacheKey(String mobile) {
        return new StringBuilder("agent_info_").append(mobile).toString();
    }

}
