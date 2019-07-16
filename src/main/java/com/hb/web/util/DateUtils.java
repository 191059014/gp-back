package com.hb.web.util;

import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ========== 日期工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.DateUtils.java, v1.0
 * @date 2019年06月05日 15时02分
 */
public class DateUtils {

    /**
     * 日志
     */
    protected static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 默认的日期格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 只有年月日的日期格式
     */
    public static final String YMD_FORMAT = "yyyy-MM-dd";

    /**
     * 默认的时区
     */
    public static final String DEFAULT_TIMEZONE = "GMT+8";

    /**
     * ########## 获取当前时间 ##########
     *
     * @return 当前时间
     */
    public static Date getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    /**
     * ########## 字符串转日期 ##########
     *
     * @param dateValue 日期字符串
     * @return 日期
     */
    public static Date str2date(String dateValue) throws ParseException {
        SimpleDateFormat dateParser = DateFormatHolder.formatFor(DEFAULT_FORMAT);
        return dateParser.parse(dateValue);
    }

    /**
     * ########## 字符串转日期 ##########
     *
     * @param dateValue  日期字符串
     * @param dateFormat 日期格式
     * @return 日期
     */
    public static Date str2date(String dateValue, String dateFormat) throws ParseException {
        SimpleDateFormat dateParser = DateFormatHolder.formatFor(dateFormat);
        return dateParser.parse(dateValue);
    }

    /**
     * ########## 日期转字符串 ##########
     *
     * @param date 日期
     * @return 字符串
     */
    public static String date2str(Date date) {
        SimpleDateFormat dateParser = DateFormatHolder.formatFor(DEFAULT_FORMAT);
        return dateParser.format(date);
    }

    /**
     * ########## 日期转字符串 ##########
     *
     * @param date       日期
     * @param dateFormat 字符串格式
     * @return 字符串
     */
    public static String date2str(Date date, String dateFormat) {
        SimpleDateFormat dateParser = DateFormatHolder.formatFor(dateFormat);
        return dateParser.format(date);
    }

    /**
     * ########## 日期格式辅助类 ##########
     */
    static final class DateFormatHolder {
        /**
         * 线程私有的
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal() {
            @Override
            protected SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference(new HashMap());
            }
        };

        /**
         * ########## 获取SimpleDateFormat对象 ##########
         *
         * @param pattern 格式
         * @return SimpleDateFormat
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        public static SimpleDateFormat formatFor(String pattern) {
            SoftReference ref = (SoftReference) THREADLOCAL_FORMATS.get();
            Map formats = (Map) ref.get();
            if (formats == null) {
                formats = new HashMap();
                THREADLOCAL_FORMATS.set(new SoftReference(formats));
            }

            SimpleDateFormat format = (SimpleDateFormat) formats.get(pattern);
            if (format == null) {
                format = new SimpleDateFormat(pattern);
                formats.put(pattern, format);
            }
            return format;
        }
    }

}
