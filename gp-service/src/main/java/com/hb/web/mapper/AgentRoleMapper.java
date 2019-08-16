package com.hb.web.mapper;

import com.hb.facade.entity.AgentRoleDO;

import java.util.List;
import java.util.Set;

public interface AgentRoleMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(AgentRoleDO record);

    AgentRoleDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AgentRoleDO record);

    int deleteByAgentId(String agentId);

    int batchInsert(List<AgentRoleDO> addList);

    Set<Integer> getRoleIdSetByAgentId(String agentId);
}