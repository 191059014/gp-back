package com.hb.web.tool;

import java.util.Random;

/**
 * ========== 验证码工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.tool.VerifyCodeTools.java, v1.0
 * @date 2019年06月16日 07时35分
 */
public class VerifyCodeTools {

    /**
     * ########## 获取手机验证码 ##########
     *
     * @return 验证码
     */
    public static Integer generateMobileVerifyCode() {
        String str = "123456789";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return Integer.parseInt(sb.toString());
    }

}
