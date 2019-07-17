package com.hb.web.api;

import com.hb.web.model.RolePermissionDO;

/**
 * ========== 角色权限 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IRolePermissionService.java, v1.0
 * @date 2019年07月17日 09时40分
 */
public interface IRolePermissionService {

    /**
     * ########## 添加角色权限关系 ##########
     *
     * @param rolePermissionDO 角色权限关系信息
     * @return 是否成功
     */
    int addRolePermission(RolePermissionDO rolePermissionDO);

}
