package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ========== 角色实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.RoleDO.java, v1.0
 * @date 2019年06月03日 11时28分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleDO extends BaseDO {

    private static final long serialVersionUID = -3368509626521238017L;
    // 主键
    private Integer roleId;
    // 角色名称
    private String roleName;
    // 标题
    private String title;
    // 描述
    private String description;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleDO() {
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}' + "," + super.toString();
    }
}
