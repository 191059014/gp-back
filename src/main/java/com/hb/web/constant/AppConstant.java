package com.hb.web.constant;

/**
 * ========== 常量类 ==========
 *
 * @author Mr.huang
 * @version com.hb.st.app.constant.AppConstant.java, v1.0
 * @date 2019年06月13日 23时06分
 */
public class AppConstant {

    // UTF-8
    public static final String UTF_8 = "UTF-8";
    // token
    public static final String TOKEN = "token";
    // secret
    public static final String SECRET = "secret";
    // signature
    public static final String SIGNATURE = "signature";
    // 手机验证码默认过期时间60秒
    public static final Long MOBILE_VERIFYCODE_EXPIRE_TIME = 3 * 60L;
    // 手机验证码缓存键
    public static final String MOBILE_VERIFYCODE_CACHE_KEY = "mobile_verifycode_";

}
