package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;

/**
 * ========== 权限表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.model.sys.PermissionDO.java, v1.0
 * @date 2019年06月03日 11时39分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@SelfTableClass(value = "t_permission", comment = "权限表")
public class PermissionDO extends BaseDO {
    // serialVersionUID
    private static final long serialVersionUID = -7962387562060600860L;
    // 主键
    @SelfTableColumn(value = "permissionId", id = true, length = 10, comment = "权限表")
    private Integer permissionId;
    // 权限名称
    @SelfTableColumn(value = "permissionName", comment = "权限名称")
    private String permissionName;
    // 资源类型
    @SelfTableColumn(value = "sourceType", length = 1, comment = "资源类型")
    private Integer sourceType;
    // 权限值
    @SelfTableColumn(value = "permissionValue", length = 100, comment = "权限值")
    private String permissionValue;

    public PermissionDO() {
    }

    public PermissionDO(String permissionName) {
        this.permissionName = permissionName;
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

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
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
