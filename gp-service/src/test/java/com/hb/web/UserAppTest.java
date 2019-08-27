package com.hb.web;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.UserAppTest.java, v1.0
 * @date 2019年08月27日 19时00分
 */
public class UserAppTest {

    public static void main(String[] args) {
        String realName = "张三丰";
        if (!StringUtils.isBlank(realName)) {
            realName = StringUtils.repeat("*", realName.length() - 1) + realName.substring(realName.length() - 1);
        }
        System.out.println(realName);

        String idCardNo = "421127199303041739";
        if (StringUtils.isNotBlank(idCardNo)) {
            idCardNo = idCardNo.substring(0, idCardNo.length() - 6) + StringUtils.repeat("*", 6);
        }
        System.out.println(idCardNo);
    }

}
