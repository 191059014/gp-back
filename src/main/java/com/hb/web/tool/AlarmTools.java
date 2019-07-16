package com.hb.web.tool;

import com.alibaba.fastjson.JSONObject;
import com.hb.web.common.Alarm;
import com.hb.web.common.AlarmContent;
import com.hb.web.util.LogUtils;
import com.hb.web.util.OkHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ========== 告警 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.tool.AlarmTools.java, v1.0
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
     * @param alarm 告警信息
     */
    public void alert(Alarm alarm) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msgtype", "text");
        jsonObject.put("text", new AlarmContent(buildAlertMessage(alarm)));
        try {
            OkHttpUtils.post(alarmUrl, jsonObject, null);
        } catch (Exception e) {
            LOGGER.error("alarm occur exception : {}", LogUtils.getStackTrace(e));
        }
    }

    /**
     * ########## 组装告警信息 ##########
     *
     * @param alarm 告警信息实体
     * @return 告警信息
     */
    private String buildAlertMessage(Alarm alarm) {
        StringBuilder sb = new StringBuilder();
        sb.append("【");
        sb.append("代理商").append(unit);
        sb.append("#").append(alarm.getModuleName());
        sb.append("#").append(alarm.getApiDesc());
        sb.append("】");
        sb.append(alarm.getMessage());
        sb.append("，请及时处理！");
        return sb.toString();
    }

}
