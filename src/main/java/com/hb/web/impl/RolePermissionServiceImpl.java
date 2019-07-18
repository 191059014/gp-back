package com.hb.web.impl;

import com.hb.web.api.IPermissionService;
import com.hb.web.api.IRolePermissionService;
import com.hb.web.mapper.RolePermissionMapper;
import com.hb.web.model.PermissionDO;
import com.hb.web.model.RolePermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private IPermissionService iPermissionService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int batchInsert(List<String> permissionValueList, Integer roleId) {
        List<PermissionDO> list = iPermissionService.findList(new PermissionDO());
        Map<String, Integer> map = list.stream().collect(Collectors.toMap(PermissionDO::getPermissionValue, PermissionDO::getPermissionId));
        List<RolePermissionDO> addList = new ArrayList<>();
        for (String permissionValue : permissionValueList) {
            addList.add(new RolePermissionDO(roleId, map.get(permissionValue)));
        }
        // 先删除角色对应的权限，再批量添加
        rolePermissionMapper.deleteByRoleId(roleId);
        return rolePermissionMapper.batchInsert(addList);
    }

}
