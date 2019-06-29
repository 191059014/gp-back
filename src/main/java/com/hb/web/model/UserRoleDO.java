package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ========== 用户-角色 关联表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.UserRoleDO.java, v1.0
 * @date 2019年06月03日 11时33分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRoleDO extends BaseDO {

    // serialVersionUID
    private static final long serialVersionUID = -2147404515536143699L;

    // 主键
    private Integer id;
    // 用户ID
    private String agentId;
    // 角色ID
    private Integer roleId;

    public UserRoleDO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
                "id=" + id +
                ", agentId=" + agentId +
                ", roleId=" + roleId +
                '}' + "," + super.toString();
    }

}
