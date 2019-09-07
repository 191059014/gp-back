package com.hb.facade.constant;

import java.math.BigDecimal;

/**
 * ========== 常量类 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.constant.GeneralConst.java, v1.0
 * @date 2019年05月31日 11时22分
 */
public class GeneralConst {

    // 一百
    public static final BigDecimal ONE_HUNDRED_D = new BigDecimal(100);
    // 一万
    public static final BigDecimal TEN_THOUSAND_D = new BigDecimal(10000);
    // 用户缓存信息的key
    public static final String USER_SESSION_KEY = "LOGIN_USER_INFO_";
    // 用户缓存信息过期时间
    public static final Long USER_SESSION_EXIRE_TIME = 10 * 60L;
    // 数据有效状态，1-有效
    public static final Integer RECORD_STATUS_Y = 1;
    // 数据有效状态，0-无效
    public static final Integer RECORD_STATUS_N = 0;
    // 请求ip缓存key
    public static final String REQUEST_IP_KEY = "REQUEST_IP_";
    // 用户缓存过期时间
    public static final Long USER_INFO_EXPIRE_TIME = 30 * 24 * 60 * 60L;
    // 代理商缓存过期时间
    public static final Long AGENT_INFO_EXPIRE_TIME = 30 * 24 * 60 * 60L;

}
