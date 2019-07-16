package com.hb.web.test;

import com.hb.web.common.Alarm;
import com.hb.web.tool.AlarmTools;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.test.TestAlarm.java, v1.0
 * @date 2019年07月15日 20时45分
 */
@RestController
public class TestAlarm {

    private Logger LOGGER = LoggerFactory.getLogger(TestAlarm.class);

    @Autowired
    AlarmTools alarmTools;

    @RequestMapping("/alert")
    public void alert() {
        Alarm alarm = new Alarm("订单", "下单接口", "下单异常");
        alarmTools.alert(alarm);
    }

    @RequestMapping("/testLogger")
    public void testLogger() {
        LOGGER.info("testLogger-测试日志打印{}={}", "张三", "20");
        testTraceId();
    }

    private void testTraceId() {
        LOGGER.info("testTraceId-测试日志打印{}={}", "张三", "20");
        LOGGER.info("hahaha");
    }

}
