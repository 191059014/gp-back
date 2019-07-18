package com.hb.web.mapper;

import com.hb.web.model.PermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {

    int deleteByPrimaryKey(Integer permissionId);

    int insertSelective(PermissionDO record);

    PermissionDO selectByPrimaryKey(Integer permissionId);

    int updateByPrimaryKeySelective(PermissionDO record);

    List<PermissionDO> findPageList(@Param("permissionDO") PermissionDO permissionDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("permissionDO") PermissionDO permissionDO);

    int batchInsert(List<PermissionDO> permissionList);

    int deleteAll();

    List<PermissionDO> findList(@Param("permissionDO") PermissionDO permissionDO);
}