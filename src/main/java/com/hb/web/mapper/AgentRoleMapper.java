package com.hb.web.mapper;

import com.hb.web.model.AgentRoleDO;

public interface AgentRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(AgentRoleDO record);

    AgentRoleDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentRoleDO record);

}