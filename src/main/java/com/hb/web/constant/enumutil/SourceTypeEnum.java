package com.hb.web.constant.enumutil;

import org.springframework.context.annotation.Primary;

/**
 * ========== 资源类型 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.constant.enumutil.SourceTypeEnum.java, v1.0
 * @date 2019年07月16日 22时52分
 */
public enum SourceTypeEnum {

    page(1, "页面"),
    button(2, "按钮");

    private Integer value;
    private String desc;

    SourceTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
