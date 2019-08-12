package com.hb.web.aliyun.service;

import com.hb.web.aliyun.model.BankCardAuthResult;
import com.hb.web.aliyun.model.IdCardAuthResult;

/**
 * ========== 实名认证 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.service.IRealNameAuth.java, v1.0
 * @date 2019年08月12日 21时52分
 */
public interface IRealNameAuth {
    /**
     * ########## 银行卡实名认证 ##########
     *
     * @param accountNo 银行卡号
     * @param idCard    身份证号
     * @param name      姓名
     * @return 实名认证结果
     */
    BankCardAuthResult bankCardAuth(String accountNo, String idCard, String name);

    /**
     * ########## 身份证实名认证 ##########
     *
     * @param cardNo   身份证号
     * @param realName 姓名
     * @return 实名认证结果
     */
    IdCardAuthResult idCardAuth(String cardNo, String realName);

}
