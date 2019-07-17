package com.hb.web.mapper;

import com.hb.web.model.PermissionDO;

public interface PermissionMapper {

    int deleteByPrimaryKey(Integer permissionId);

    int insertSelective(PermissionDO record);

    PermissionDO selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(PermissionDO record);

}