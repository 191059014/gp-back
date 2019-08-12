package com.hb.web.aliyun.constant.enumutil;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== 短信发送响应枚举 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.aliyun.constant.enumutil.SMSResEnum.java, v1.0
 * @date 2019年08月12日 19时37分
 */
public enum SMSResEnum {

    success("Success", "1", "1", "1"),
    error_param("1", "1", "1", "1"),
    error_mobile("1", "1", "1", "1"),
    error_produt("1", "1", "1", "1"),
    error_price("1", "1", "1", "1"),
    error_repeat("1", "1", "1", "1"),
    err_template("1", "1", "1", "1"),
    error("1", "1", "1", "1");

    private String code;
    private String message;
    private String desc;
    private String hint;

    SMSResEnum(String code, String message, String desc, String hint) {
        this.code = code;
        this.message = message;
        this.desc = desc;
        this.hint = hint;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDesc() {
        return desc;
    }

    public String getHint() {
        return hint;
    }

    /**
     * ########## 根据code匹配 ##########
     *
     * @param code code
     * @return SMSResEnum
     */
    public static SMSResEnum match(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (SMSResEnum smsResEnum : SMSResEnum.values()) {
            if (StringUtils.equals(smsResEnum.code, code)) {
                return smsResEnum;
            }
        }
        return null;
    }

}
