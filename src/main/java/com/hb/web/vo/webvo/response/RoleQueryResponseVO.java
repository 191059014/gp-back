package com.hb.web.vo.webvo.response;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * ========== 查询角色vo ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.webvo.response.RoleQueryResponseVO.java, v1.0
 * @date 2019年07月18日 17时00分
 */
public class RoleQueryResponseVO implements Serializable {

    private Integer roleId;
    private String roleName;
    private String description;
    private Date createTime;
    private Date updateTime;

    Set<String> permissionValueSet;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<String> getPermissionValueSet() {
        return permissionValueSet;
    }

    public void setPermissionValueSet(Set<String> permissionValueSet) {
        this.permissionValueSet = permissionValueSet;
    }
}
