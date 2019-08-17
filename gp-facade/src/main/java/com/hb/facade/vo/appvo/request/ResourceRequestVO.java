package com.hb.facade.vo.appvo.request;

import java.io.Serializable;

/**
 * ========== 获取资源请求vo ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.ResourceRequestVO.java, v1.0
 * @date 2019年08月14日 09时28分
 */
public class ResourceRequestVO implements Serializable {

    private static final long serialVersionUID = 2496489955283313726L;

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ResourceRequestVO{" +
                "path='" + path + '\'' +
                '}';
    }
}
