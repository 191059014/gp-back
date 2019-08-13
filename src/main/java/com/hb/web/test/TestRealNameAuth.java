package com.hb.web.test;

import com.hb.web.aliyun.constant.SMSTemplate;
import com.hb.web.aliyun.model.BankCardAuthResult;
import com.hb.web.aliyun.model.IdCardAuthResult;
import com.hb.web.aliyun.model.SMSSendResult;
import com.hb.web.aliyun.service.IRealNameAuth;
import com.hb.web.aliyun.service.ISMS;
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
@RequestMapping("realNameAuth")
public class TestRealNameAuth {

    @Autowired
    private IRealNameAuth realNameAuth;

    @GetMapping("/testBankCardAuth")
    public BankCardAuthResult testBankCardAuth() {
        BankCardAuthResult bankCardAuthResult = realNameAuth.bankCardAuth("6217000010060068296", "421127199303041738", "黄彪");
        return bankCardAuthResult;
    }

    @GetMapping("/testIDCardAuth")
    public IdCardAuthResult testIDCardAuth() {
        IdCardAuthResult idCardAuthResult = realNameAuth.idCardAuth("421127199303041736", "黄彪");
        return idCardAuthResult;
    }

}
