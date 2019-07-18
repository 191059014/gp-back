package com.hb.web.mapper;

import com.hb.web.model.RolePermissionDO;

import java.util.List;

public interface RolePermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RolePermissionDO record);

    RolePermissionDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermissionDO record);

    int batchInsert(List<RolePermissionDO> addList);

    int deleteByRoleId(Integer roleId);
}