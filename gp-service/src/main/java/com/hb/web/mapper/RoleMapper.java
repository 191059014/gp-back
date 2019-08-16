package com.hb.web.mapper;

import com.hb.facade.entity.RoleDO;
import com.hb.web.vo.webvo.response.RoleTreeResponseVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(Integer roleId);

    int insertSelective(RoleDO record);

    RoleDO selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleDO record);

    List<RoleDO> findPageList(@Param("roleDO") RoleDO roleDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("roleDO") RoleDO roleDO);

    List<RoleTreeResponseVO> findRoleTree();
}