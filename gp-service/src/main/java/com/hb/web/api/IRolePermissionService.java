package com.hb.web.api;

import java.util.List;
import java.util.Set;

/**
 * ========== 角色权限 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IRolePermissionService.java, v1.0
 * @date 2019年07月17日 09时40分
 */
public interface IRolePermissionService {

    int batchInsert(List<String> permissionValueList, Integer roleId);

    Set<Integer> getPermissionIdSetByRoleIdSet(Set<Integer> roleIdSet);
}
