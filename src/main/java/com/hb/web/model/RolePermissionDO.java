package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.web.annotation.SelfTableClass;
import com.hb.web.annotation.SelfTableColumn;

/**
 * ========== 角色-权限 关联表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.RolePermissionDO.java, v1.0
 * @date 2019年06月03日 11时47分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_role_permission", comment = "角色权限关联表")
public class RolePermissionDO extends BaseDO {
    // serialVersionUID
    private static final long serialVersionUID = -5746573035801321749L;
    // 主键
    @SelfTableColumn(value = "id", id = true, length = 10, comment = "主键")
    private Integer id;
    // 角色ID
    @SelfTableColumn(value = "roleId", length = 10, comment = "角色ID")
    private Integer roleId;
    // 权限ID
    @SelfTableColumn(value = "permissionId", length = 10, comment = "权限ID")
    private Integer permissionId;

    public RolePermissionDO() {
    }

    public RolePermissionDO(Integer roleId, Integer permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
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
