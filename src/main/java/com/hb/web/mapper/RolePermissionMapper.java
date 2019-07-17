package com.hb.web.mapper;

import com.hb.web.model.RolePermissionDO;

public interface RolePermissionMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(RolePermissionDO record);

    RolePermissionDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermissionDO record);

}