package com.hb.web.mapper;

import com.hb.web.model.RoleDO;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insertSelective(RoleDO record);

    RoleDO selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleDO record);

}