package com.hb.facade.common;

import com.alibaba.fastjson.JSON;
import com.hb.unic.util.helper.JsonFileParseHelper;
import com.hb.unic.util.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SystemConfig implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfig.class);

    // app.json
    private static AppJson appJson = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加载app.json
        loadAppJson();
    }

    /**
     * 加载app.json
     */
    private void loadAppJson() {
        if (appJson == null) {
            String json = JsonFileParseHelper.readJsonFile2StringByStream("static/app/app.json");
            System.out.println(json);
            appJson = JSON.parseObject(json, AppJson.class);
        }
    }

    /**
     * 判断日期是不是特殊节假日
     *
     * @param date 日期
     * @return boolean
     */
    public static boolean isSpecialHoliday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        String month = StringUtils.fillZero(calendar.get(Calendar.MONTH) + 1 + "", 2);
        String day = StringUtils.fillZero(calendar.get(Calendar.DATE) + "", 2);
        String now = new StringBuilder().append(year).append(month).append(day).toString();
        LOGGER.info("isSpecialHoliday#now：{}" + now);
        if (appJson.getSpecialHoliday().contains(now)) {
            return true;
        }
        return false;
    }

    /**
     * 获取AppJson配置
     *
     * @return AppJson
     */
    public static AppJson getAppJson() {
        return appJson;
    }

}
