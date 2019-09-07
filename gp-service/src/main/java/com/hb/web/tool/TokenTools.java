package com.hb.web.tool;

import com.alibaba.fastjson.JSON;
import com.hb.facade.entity.UserDO;
import com.hb.unic.cache.service.ICacheService;
import com.hb.unic.util.util.EncryptUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * ========== Token工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.util.TokenTools.java, v1.0
 * @date 2019年06月13日 21时14分
 */
public class TokenTools {

    /**
     * token缓存的key
     */
    private static final String TOKEN_CACHE_KEY_PREFIX = "token_";

    /**
     * token缓存的过期时间
     */
    private static final Long TOKEN_EXPIRE_TIME = 30 * 24 * 60 * 60L;

    /**
     * ########## 生成Token ##########
     */
    public static String generate() {
        String token = new StringBuilder()
                .append(String.valueOf(System.currentTimeMillis()))
                .append(new Random().nextInt(1000000000) + "")
                .toString();
        return token;
    }

    /**
     * ########## 获取加密Token ##########
     */
    public static String encrypt(String token) {
        return EncryptUtils.encode(token);
    }

    /**
     * ########## 将token作为key放入缓存，用户信息作为value ##########
     *
     * @param userDO            用户信息
     * @param token             用户token
     * @param redisCacheService redis操作类对象
     */
    public static void set(UserDO userDO, String token, ICacheService redisCacheService) {
        String key = getTokenCacheKey(token);
        redisCacheService.set(key, userDO, TOKEN_EXPIRE_TIME);
    }

    /**
     * ########## 通过token获取用户信息 ##########
     *
     * @param token             用户token
     * @param redisCacheService redis操作类对象
     * @return key
     */
    public static UserDO get(String token, ICacheService redisCacheService) {
        String agentStr = redisCacheService.get(getTokenCacheKey(token));
        if (StringUtils.isBlank(agentStr)) {
            return null;
        }
        UserDO userDO = JSON.parseObject(agentStr, UserDO.class);
        return userDO;
    }

    /**
     * ########## 获取token缓存key ##########
     *
     * @param token 用户token
     * @return key
     */
    private static String getTokenCacheKey(String token) {
        return new StringBuilder().append(TOKEN_CACHE_KEY_PREFIX).append(token).toString();
    }

}
