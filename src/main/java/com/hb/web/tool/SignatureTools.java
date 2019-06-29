package com.hb.web.tool;

import com.hb.web.util.EncryptUtils;

/**
 * ========== 签名工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.tool.SignatureTools.java, v1.0
 * @date 2019年06月13日 22时53分
 */
public class SignatureTools {

    /**
     * ########## 获取加密的签名 ##########
     *
     * @param requestUrl 请求的地址
     * @param bodyString 请求的body信息
     * @param secret     secret
     * @return 加密后的签名
     */
    public static String signature(String requestUrl, String bodyString, String secret) {
        String message = new StringBuilder().append(requestUrl).append(bodyString).append(secret).toString();
        return EncryptUtils.encode(message);
    }

}
