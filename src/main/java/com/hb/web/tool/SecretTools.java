package com.hb.web.tool;

import java.util.Random;

/**
 * ========== secret工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.util.TokenTools.java, v1.0
 * @date 2019年06月13日 21时14分
 */
public class SecretTools {

    /**
     * secret缓存的key
     */
    private static final String SECRET_CACHE_KEY_PREFIX = "secret_";

    /**
     * ########## 生成secret ##########
     */
    public static String generate() {
        String secret = new StringBuilder()
                .append(String.valueOf(System.currentTimeMillis()))
                .append(new Random().nextInt(1000000000))
                .toString();
        return secret;
    }

}
