package com.hb.web.impl;

import com.hb.web.api.IPermissionService;
import com.hb.web.mapper.PermissionMapper;
import com.hb.web.model.PermissionDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ========== 权限 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.PermissionServiceImpl.java, v1.0
 * @date 2019年07月17日 11时16分
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDO> findPageList(PermissionDO permissionDO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public Integer findCount(PermissionDO permissionDO) {
        return null;
    }

    @Override
    public int addPermission(PermissionDO permissionDO) {
        return permissionMapper.insertSelective(permissionDO);
    }

    @Override
    public int updateByPrimaryKeySelective(PermissionDO permissionDO) {
        return permissionMapper.updateByPrimaryKeySelective(permissionDO);
    }

    @Override
    public int deleteByPrimaryKey(Integer permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }

}
