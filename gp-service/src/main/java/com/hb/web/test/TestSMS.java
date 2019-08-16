package com.hb.web.test;

import com.hb.remote.constant.SMSTemplate;
import com.hb.remote.model.SMSSendResult;
import com.hb.remote.service.ISMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.test.TestSMS.java, v1.0
 * @date 2019年08月12日 21时08分
 */
@RestController
@RequestMapping("sms")
public class TestSMS {

    @Autowired
    private ISMS ISMS_106;

    @GetMapping("/test")
    public void test() {
        SMSSendResult send = ISMS_106.send("18310673016", 1234, SMSTemplate.template_1, 3L);
        System.out.println(send);
    }

}
