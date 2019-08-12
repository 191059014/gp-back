package com.hb.web.aliyun.service.impl;

import com.alibaba.fastjson.JSON;
import com.hb.web.aliyun.constant.enumutil.SMSResEnum;
import com.hb.web.aliyun.model.out.SMS106OutRes;
import com.hb.web.aliyun.model.SMSSendResult;
import com.hb.web.aliyun.service.ISMS;
import com.hb.web.aliyun.util.HttpUtils;
import com.hb.web.tool.AlarmTools;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.util.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ========== 106短信 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.service.impl.SMS_106.java, v1.0
 * @date 2019年08月12日 19时59分
 */
@Service("sms_106")
public class SMS_106 implements ISMS {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SMS_106.class);

    @Autowired
    public AlarmTools alarmTools;

    @Value("${sms.host}")
    private String host;

    @Value("${sms.path}")
    private String path;

    @Value("${sms.appcode}")
    private String appcode;

    @Override
    public SMSSendResult send(String mobile, Integer verifyCode, String smsTemplate, Long expireTime, Object... param) {
        LOGGER.info("SMS_106#send 入参:{}={}={}={}={}", mobile, verifyCode, smsTemplate, expireTime, param);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        /**
         * 短信内容
         */
        String content = smsTemplate;
        content = content.replace("{verifyCode}", verifyCode + "");
        content = content.replace("{expireTime}", expireTime + "");
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                String symbol = "{" + i + "}";
                if (!smsTemplate.contains(symbol)) {
                    return new SMSSendResult(mobile, verifyCode, SMSResEnum.err_template);
                }
                content = content.replace(symbol, String.valueOf(param[i]));
            }
        }
        LOGGER.info("SMS_106 content:{}", content);
        Map<String, String> querys = new HashMap<>();
        querys.put("content", content);
        querys.put("mobile", mobile);
        Map<String, String> bodys = new HashMap<>();
        try {
            /**
             * http调用短信服务
             */
            HttpResponse response = HttpUtils.doPost(host, path, "POST", headers, querys, bodys);
            String bodyString = EntityUtils.toString(response.getEntity());
            LOGGER.info("SMS_106 result:{}", bodyString);
            SMS106OutRes res = JSON.parseObject(bodyString, SMS106OutRes.class);
            if (!StringUtils.equals(SMSResEnum.success.getCode(), res.getReturnStatus())) {
                return new SMSSendResult(mobile, verifyCode, SMSResEnum.error);
            }
            return new SMSSendResult(mobile, verifyCode, SMSResEnum.success);
        } catch (Exception e) {
            LOGGER.error("SMS_106 Exception:{}", LogUtils.getStackTrace(e));
            alarmTools.alert("SMS_106", "阿里云服务", "短信发送", "短信发送异常：" + e.getMessage());
            return new SMSSendResult(mobile, verifyCode, SMSResEnum.error);
        }
    }

}
