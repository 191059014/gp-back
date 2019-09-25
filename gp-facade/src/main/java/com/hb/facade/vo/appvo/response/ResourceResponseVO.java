package com.hb.facade.vo.appvo.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * ========== 获取资源响应vo ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.ResourceRequestVO.java, v1.0
 * @date 2019年08月14日 09时28分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceResponseVO implements Serializable {

    private static final long serialVersionUID = 2496489955283313726L;

    private String json;

    public ResourceResponseVO(String json) {
        this.json = json;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "ResourceResponseVO{" +
                "json='" + json + '\'' +
                '}';
    }
}
