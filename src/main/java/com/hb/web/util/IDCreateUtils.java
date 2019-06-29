package com.hb.web.util;

import java.util.Calendar;
import java.util.Date;

/**
 * ========== 生成ID ==========
 *
 * @author Mr.huang
 * @version com.hb.web.util.IDCreateUtils.java, v1.0
 * @date 2019年06月11日 15时25分
 */
public class IDCreateUtils {

    /**
     * 生成多个对象，减少同步锁开销
     */
    private static final IDCreateUtils agentIdCreator = new IDCreateUtils();
    private static final IDCreateUtils userIdCreator = new IDCreateUtils();

    public static IDCreateUtils agentIdCreator() {
        return agentIdCreator;
    }

    public static IDCreateUtils userIdCreator() {
        return userIdCreator;
    }

    /**
     * ########## 生成UserId ##########
     *
     * @return 用户ID
     */
    public synchronized String generateUserId() {
        String s1 = String.valueOf(System.currentTimeMillis());
        String s2 = String.valueOf(System.currentTimeMillis());
        System.out.println("s1=" + s1);
        System.out.println("s2=" + s2);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("generateUserId exception:" + LogUtils.getStackTrace(e));
        }
        return new StringBuilder().append("U").append(getTimeString(DateUtils.getCurrentDate())).toString();
    }

    /**
     * ########## 生成agentId ##########
     *
     * @return 代理商ID
     */
    public synchronized String generateAgentId() {
        return new StringBuilder().append("A").append(getTimeString(DateUtils.getCurrentDate())).toString();
    }

    /**
     * ########## 获取年月日时分秒毫秒拼接字符串 ##########
     *
     * @param currentDate 日期
     * @return 字符串
     */
    private static String getTimeString(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int milliSecond = calendar.get(Calendar.MILLISECOND);
        String result = new StringBuilder()
                .append(year)
                .append(addZero(month + "", 2))
                .append(addZero(day + "", 2))
                .append(addZero(hour + "", 2))
                .append(addZero(minute + "", 2))
                .append(addZero(second + "", 2))
                .append(addZero(milliSecond + "", 3))
                .toString();
        return result;
    }

    /**
     * ########## 左边补0 ##########
     *
     * @param str    原字符串
     * @param length 期望的长度
     * @return 补0后的字符串
     */
    private static String addZero(String str, int length) {
        int addLength = length - str.length();
        StringBuilder zero = new StringBuilder();
        for (int i = 0; i < addLength; i++) {
            zero.append("0");
        }
        zero.append(str);
        return zero.toString();
    }

}
