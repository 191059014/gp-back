package com.hb.web.tool;

import com.alibaba.fastjson.JSONObject;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.logger.TraceIdUtils;
import com.hb.unic.util.util.OkHttpUtils;
import com.hb.web.common.AlarmContent;
import com.hb.web.util.LogUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ========== 告警 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.helper.AlarmTools.java, v1.0
 * @date 2019年07月15日 20时04分
 */
@Component
public class AlarmTools {
    /**
     * The constant LOGGER.
     */
    protected static Logger LOGGER = LoggerFactory.getLogger(AlarmTools.class);

    @Value("${gpweb.unit}")
    private String unit;

    @Value("${gpweb.alarmUrl}")
    private String alarmUrl;

    /**
     * ########## 告警 ##########
     *
     * @param source  消息产生的来源
     * @param module  模块名
     * @param apiDesc 接口描述
     * @param message 展示信息
     */
    public void alert(String source, String module, String apiDesc, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgtype", "text");
        String content = buildAlertMessage(source, module, apiDesc, message);
        jsonObject.put("text", new AlarmContent(content));
        try {
            String bodyString = OkHttpUtils.post(alarmUrl, jsonObject, null);
            LOGGER.info("invoke alarm resurn: {}", bodyString);
        } catch (Exception e) {
            LOGGER.error("alarm occur exception : {}", LogUtils.getStackTrace(e));
        }
    }

    /**
     * ########## 组装告警信息 ##########
     *
     * @param source  消息产生的来源
     * @param module  模块名
     * @param apiDesc 接口描述
     * @param message 展示信息
     * @return 告警信息
     */
    private String buildAlertMessage(String source, String module, String apiDesc, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("【");
        sb.append("代理商").append(unit);
        sb.append("#").append(source);
        sb.append("#").append(module);
        sb.append("#").append(apiDesc);
        sb.append("】");
        sb.append(message);
        sb.append("，请及时处理！");
        sb.append("【traceId:").append(TraceIdUtils.getTraceId()).append("】");
        return sb.toString();
    }

}
