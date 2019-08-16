package com.hb.web.vo.webvo.response;

import java.io.Serializable;

/**
 * ========== 角色树 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.webvo.response.RoleTreeResponseVO.java, v1.0
 * @date 2019年07月18日 19时31分
 */
public class RoleTreeResponseVO implements Serializable {

    private String id;
    private String label;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
