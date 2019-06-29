package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ========== 角色-权限 关联表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.RolePermissionDO.java, v1.0
 * @date 2019年06月03日 11时47分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RolePermissionDO extends BaseDO {

    private static final long serialVersionUID = -5746573035801321749L;
    // 主键
    private Integer id;
    // 角色ID
    private Integer roleId;
    // 权限ID
    private Integer permissionId;

    public RolePermissionDO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermissionDO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}' + "," + super.toString();
    }
}
