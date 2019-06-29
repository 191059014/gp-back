package com.hb.web.mapper;

import com.hb.web.model.RoleDO;

public interface RoleMapper {

    int insert(RoleDO record);

    int insertSelective(RoleDO record);

}