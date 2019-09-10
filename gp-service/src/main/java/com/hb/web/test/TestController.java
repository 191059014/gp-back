package com.hb.web.test;

import com.hb.facade.calc.StockTools;
import com.hb.facade.common.SystemConfig;
import com.hb.unic.util.util.DateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.test.TestController.java, v1.0
 * @date 2019年09月10日 14时04分
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public void test() throws ParseException {
        Date date1 = DateUtils.str2date("2019-09-10 16:01:00", DateUtils.DEFAULT_FORMAT);
        Date date = StockTools.calcSellDate(date1, 3);
        System.out.println(DateUtils.date2str(date, DateUtils.DEFAULT_FORMAT));
    }

}
