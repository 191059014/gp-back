package com.hb.web.constant.enumutil;

import org.apache.commons.lang3.StringUtils;

/**
 * ========== 交易所类型 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.constant.enumutil.ExchangeTypeEnum.java, v1.0
 * @date 2019年05月31日 11时28分
 */
public enum ExchangeTypeEnum {

    shang_hai("sh", "", "上海交易所"),
    shen_zhen("s_sz", "", "深圳交易所"),
    shang_zhen_zhi_shu("s_sh", "000001", "上证指数"),
    shen_zhen_cheng_zhi("s_sz", "399001", "深圳成指"),
    chuang_ye_ban_zhi("s_sz", "399006", "创业板指");

    private String name;
    private String code;
    private String desc;

    ExchangeTypeEnum(String name, String code, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * ########## 普通股票匹配规则 ##########
     *
     * @param code 股票代码
     * @return 封装好的查询参数
     */
    public static String commonRule(String code) {
        if (code.startsWith("sz")) {
            return code.replace("sz", shen_zhen.name);
        }
        return code;
    }

    /**
     * ########## 特殊股票匹配规则 ##########
     *
     * @param code 股票代码
     * @return 封装好的查询参数
     */
    public static String specialRule(String code) {
        if (StringUtils.equals(shang_zhen_zhi_shu.code, code)) {
            return shang_zhen_zhi_shu.name + code;
        } else if (StringUtils.equals(shen_zhen_cheng_zhi.code, code)) {
            return shen_zhen_cheng_zhi.name + code;
        } else if (StringUtils.equals(chuang_ye_ban_zhi.code, code)) {
            return chuang_ye_ban_zhi.name + code;
        }
        return null;
    }

}
