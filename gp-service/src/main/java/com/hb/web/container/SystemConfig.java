package com.hb.web.container;

import com.alibaba.fastjson.JSON;
import com.hb.unic.util.helper.JsonFileParseHelper;
import com.hb.unic.util.util.StringUtils;
import com.hb.web.base.AppJson;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class SystemConfig implements InitializingBean {

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
            String json = JsonFileParseHelper.readJsonFile2String("static/app/app.json");
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
        System.out.println(now);
        if (appJson.getSpecialHoliday().contains(now)) {
            return true;
        }
        return false;
    }

}
