package com.hb.web.impl;

import com.hb.web.api.IRolePermissionService;
import com.hb.web.mapper.RolePermissionMapper;
import com.hb.web.model.RolePermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ========== 角色权限 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.RolePermissionServiceImpl.java, v1.0
 * @date 2019年07月17日 11时14分
 */
@Service
public class RolePermissionServiceImpl implements IRolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int addRolePermission(RolePermissionDO rolePermissionDO) {
        return rolePermissionMapper.insertSelective(rolePermissionDO);
    }

}
