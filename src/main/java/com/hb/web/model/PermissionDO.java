package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ========== 权限表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.PermissionDO.java, v1.0
 * @date 2019年06月03日 11时39分
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermissionDO extends BaseDO {

    //serialVersionUID
    private static final long serialVersionUID = -7962387562060600860L;

    // 主键
    private Integer permissionId;
    // 权限名称
    private String permissionName;
    // 资源类型
    private String sourceType;
    // 权限值
    private String permissionValue;

    public PermissionDO() {
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    @Override
    public String toString() {
        return "PermissionDO{" +
                "permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", permissionValue='" + permissionValue + '\'' +
                '}' + "," + super.toString();
    }
}
