package com.hb.web.mapper;

import com.hb.facade.entity.PermissionDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

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

    Set<String> getPermissionValueSetByPermissionIds(@Param("permissionSet") Set<Integer> permissionSet, @Param("sourceType") Integer sourceType);

    Set<String> getAllPermissionValueSet();

}