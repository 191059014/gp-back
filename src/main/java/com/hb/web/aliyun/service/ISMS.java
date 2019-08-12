package com.hb.web.aliyun.service;

import com.hb.web.aliyun.model.SMSSendResult;

/**
 * ========== 短信发送接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.service.ISMS.java, v1.0
 * @date 2019年08月12日 19时31分
 */
public interface ISMS {

    /**
     * ########## 短信发送接口 ##########
     *
     * @param mobile      手机号
     * @param verifyCode  验证码
     * @param smsTemplate 消息模板
     * @param expireTime  过期时间
     * @param param       模板参数
     * @return SMSSendResult
     */
    SMSSendResult send(String mobile, Integer verifyCode, String smsTemplate, Long expireTime, Object... param);

}
