package com.hb.facade.enumutil;

import org.springframework.context.annotation.Primary;

/**
 * ========== 资源类型 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.enumutil.SourceTypeEnum.java, v1.0
 * @date 2019年07月16日 22时52分
 */
public enum SourceTypeEnum {

    page(1, "页面"),
    button(2, "按钮");

    private Integer value;
    private String name;

    SourceTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
