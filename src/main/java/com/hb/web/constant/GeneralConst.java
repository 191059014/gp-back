package com.hb.web.constant;

import java.math.BigDecimal;

/**
 * ========== 常量类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.constant.GeneralConst.java, v1.0
 * @date 2019年05月31日 11时22分
 */
public class GeneralConst {

    // 新浪股票实时行情url
    public static final String SINA_URL = "http://hq.sinajs.cn";
    // 新浪股票实时行情参数
    public static final String SINA_URL_PARAM = "list";
    // 一百
    public static final BigDecimal ONE_HUNDRED_D = new BigDecimal(100);
    // 一万
    public static final BigDecimal TEN_THOUSAND_D = new BigDecimal(10000);
    // 用户缓存信息的key
    public static final String USER_SESSION_KEY = "LOGIN_USER_INFO_";
    // 用户缓存信息过期时间
    public static final Long USER_SESSION_EXIRE_TIME = 600L;
    // 数据有效状态，1-有效
    public static final Integer RECORD_STATUS_Y = 1;
    // 数据有效状态，0-无效
    public static final Integer RECORD_STATUS_N = 0;

}
