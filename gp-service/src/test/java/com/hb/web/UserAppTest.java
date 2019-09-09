package com.hb.web;

import com.hb.facade.calc.StockTools;
import com.hb.facade.enumutil.SourceTypeEnum;
import com.hb.unic.util.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.UserAppTest.java, v1.0
 * @date 2019年08月27日 19时00分
 */
public class UserAppTest {

    public static void main(String[] args) throws ParseException {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        int nowDate = c1.get(Calendar.DATE);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(DateUtils.str2date("2019-09-10 00:19:27",DateUtils.DEFAULT_FORMAT));
        int buyDate = c2.get(Calendar.DATE);
        Calendar c3 = Calendar.getInstance();
        c3.setTime(DateUtils.str2date("2019-09-11 14:50:00",DateUtils.DEFAULT_FORMAT));
        int delayEndDate = c3.get(Calendar.DATE);
        System.out.println(nowDate);
        System.out.println(buyDate);
        System.out.println(delayEndDate);

        Date date = StockTools.calcSellDate(DateUtils.str2date("2019-09-10 00:40:45", DateUtils.DEFAULT_FORMAT), 2);
        System.out.println(DateUtils.date2str(date,DateUtils.DEFAULT_FORMAT));
    }

}
