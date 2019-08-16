package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

/**
 * ========== 角色实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.RoleDO.java, v1.0
 * @date 2019年06月03日 11时28分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@SelfTableClass(value = "t_role", comment = "角色表")
public class RoleDO extends BaseDO {
    // serialVersionUID
    private static final long serialVersionUID = -3368509626521238017L;
    // 主键
    @SelfTableColumn(value = "roleId", id = true, length = 10, comment = "角色ID")
    private Integer roleId;
    // 角色名称
    @SelfTableColumn(value = "roleName", comment = "角色名称")
    private String roleName;
    // 描述
    @SelfTableColumn(value = "description", comment = "描述")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleDO() {
    }

    public RoleDO(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleDO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}' + "," + super.toString();
    }
}
