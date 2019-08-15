package com.hb.web.test;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.tool.AlarmTools;
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
        alarmTools.alert("测试", "测试", "测试", "测试");
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
