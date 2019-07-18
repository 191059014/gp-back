package com.hb.web.impl;

import com.hb.web.api.IPermissionService;
import com.hb.web.api.IRolePermissionService;
import com.hb.web.api.IRoleService;
import com.hb.web.mapper.RoleMapper;
import com.hb.web.model.RoleDO;
import com.hb.web.util.PageUtils;
import com.hb.web.vo.webvo.response.RoleTreeResponseVO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ========== 角色 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.RoleServiceImpl.java, v1.0
 * @date 2019年07月17日 11时21分
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @Autowired
    private IPermissionService iPermissionService;

    @Override
    public List<RoleDO> findPageList(RoleDO roleDO, Integer pageNum, Integer pageSize) {
        return roleMapper.findPageList(roleDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(RoleDO roleDO) {
        return roleMapper.findCount(roleDO);
    }

    @Override
    public int addRole(RoleDO roleDO) {
        return roleMapper.insertSelective(roleDO);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleDO roleDO) {
        return roleMapper.updateByPrimaryKeySelective(roleDO);
    }

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public Set<String> getPermissionByRoleId(Integer roleId) {
        Set<Integer> roleIdSet = new HashSet<>();
        roleIdSet.add(roleId);
        Set<Integer> permissionIdSet = iRolePermissionService.getPermissionIdSetByRoleIdSet(roleIdSet);
        if (CollectionUtils.isEmpty(permissionIdSet)) {
            return null;
        }
        Set<String> permissionValueSet = iPermissionService.getPermissionValueSetByPermissionIds(permissionIdSet, null);
        return permissionValueSet;
    }

    @Override
    public List<RoleTreeResponseVO> findRoleTree() {
        return roleMapper.findRoleTree();
    }

}
