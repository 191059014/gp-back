package com.hb.web.aliyun.service.impl;

import com.alibaba.fastjson.JSON;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.aliyun.constant.enumutil.BankCardAuthResEnum;
import com.hb.web.aliyun.constant.enumutil.IdCardAuthResEnum;
import com.hb.web.aliyun.model.BankCardAuthResult;
import com.hb.web.aliyun.model.IdCardAuthResult;
import com.hb.web.aliyun.model.out.BankCardOutRes;
import com.hb.web.aliyun.model.out.IdCardOutRes;
import com.hb.web.aliyun.service.IRealNameAuth;
import com.hb.web.aliyun.util.HttpUtils;
import com.hb.web.tool.AlarmTools;
import com.hb.web.util.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ========== 实名认证实现类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.service.impl.RealNameAuthImpl.java, v1.0
 * @date 2019年08月12日 22时32分
 */
@Service("realNameAuth")
public class RealNameAuthImpl implements IRealNameAuth {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SMS_106.class);

    @Autowired
    public AlarmTools alarmTools;

    @Value("${idcard.host}")
    private String idcard_host;
    @Value("${idcard.path}")
    private String idcard_path;
    @Value("${idcard.appcode}")
    private String idcard_appcode;

    @Value("${bankcard.host}")
    private String bankcard_host;
    @Value("${bankcard.path}")
    private String bankcard_path;
    @Value("${bankcard.appcode}")
    private String bankcard_appcode;

    @Override
    public BankCardAuthResult bankCardAuth(String accountNo, String idCard, String name) {
        LOGGER.info("RealNameAuthImpl#bankCardAuth 入参:{}={}={}", accountNo, idCard, name);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + bankcard_appcode);

        Map<String, String> querys = new HashMap<>();
        querys.put("accountNo", accountNo);
        querys.put("idCard", idCard);
        querys.put("name", name);
        try {
            /**
             * http调用银行卡实名认证
             */
            HttpResponse response = HttpUtils.doGet(bankcard_host, bankcard_path, "GET", headers, querys);
            String bodyString = EntityUtils.toString(response.getEntity());
            LOGGER.info("RealNameAuthImpl#bankCardAuth result:{}", bodyString);
            BankCardOutRes res = JSON.parseObject(bodyString, BankCardOutRes.class);
            if (!StringUtils.equals(BankCardAuthResEnum.success.getCode(), res.getStatus())) {
                return new BankCardAuthResult(BankCardAuthResEnum.match(res.getStatus()), res);
            }
            return new BankCardAuthResult(BankCardAuthResEnum.success, res);
        } catch (Exception e) {
            LOGGER.error("RealNameAuthImpl#bankCardAuth Exception:{}", LogUtils.getStackTrace(e));
            alarmTools.alert("RealNameAuthImpl", "阿里云服务", "银行卡实名认证", "异常：" + e.getMessage());
            return new BankCardAuthResult(BankCardAuthResEnum.error, null);
        }
    }

    @Override
    public IdCardAuthResult idCardAuth(String cardNo, String realName) {
        LOGGER.info("RealNameAuthImpl#idCardAuth 入参:{}={}", cardNo, realName);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + idcard_appcode);

        Map<String, String> querys = new HashMap<>();
        querys.put("cardNo", cardNo);
        querys.put("realName", realName);
        try {
            /**
             * http调用身份证实名认证
             */
            HttpResponse response = HttpUtils.doGet(idcard_host, idcard_path, "GET", headers, querys);
            String bodyString = EntityUtils.toString(response.getEntity());
            LOGGER.info("RealNameAuthImpl#idCardAuth result:{}", bodyString);
            IdCardOutRes res = JSON.parseObject(bodyString, IdCardOutRes.class);
            if (!StringUtils.equals(IdCardAuthResEnum.success.getCode(), res.getError_code())) {
                return new IdCardAuthResult(IdCardAuthResEnum.match(res.getError_code()), res);
            }
            if (res.getResult() == null || !res.getResult().getIsok()) {
                return new IdCardAuthResult(IdCardAuthResEnum.failed, res);
            }
            return new IdCardAuthResult(IdCardAuthResEnum.success, res);
        } catch (Exception e) {
            LOGGER.error("RealNameAuthImpl#idCardAuth Exception:{}", LogUtils.getStackTrace(e));
            alarmTools.alert("RealNameAuthImpl", "阿里云服务", "身份证实名认证", "异常：" + e.getMessage());
            return new IdCardAuthResult(IdCardAuthResEnum.error, null);
        }
    }

}
