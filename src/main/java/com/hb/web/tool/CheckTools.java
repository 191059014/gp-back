package com.hb.web.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ========== 所有校验 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.tool.CheckTools.java, v1.0
 * @date 2019年06月22日 14时47分
 */
public class CheckTools {


    /**
     * ########## 判断是不是手机号码 ##########
     *
     * @param mobile 手机号
     * @return true-是手机号码
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile) || mobile.length() != 11) {
            return false;
        }
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobile);
        boolean isMatch = m.matches();
        return isMatch;
    }

    /**
     * ########## 校验密码 ##########
     * 规则：6-15位数字或者字母[0-9A-Za-z]
     *
     * @param password 密码
     * @return true为校验通过
     */
    public static boolean checkPassword(String password) {
        int length = password.length();
        if (!(length >= 6 && length <= 15)) {
            return false;
        }
        for (int i = password.length(); --i >= 0; ) {
            int chr = password.charAt(i);
            boolean pass = false;
            // [0-9A-Za-z]校验
            if ((chr >= 48 && chr <= 57) || (chr >= 65 && chr <= 90) || (chr >= 97 && chr <= 122)) {
                pass = true;
            }
            if (!pass) {
                return false;
            }
        }
        return true;
    }

}
