package com.hb.web.mapper;

import com.hb.web.model.AgentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentMapper {

    int insertSelective(AgentDO record);

    List<AgentDO> findAgentList(@Param("agentDO") AgentDO agentDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("agentDO") AgentDO agentDO);

    AgentDO findAgent(@Param("agentDO") AgentDO agentDO);

    Integer updateAgentById(@Param("agentId") String agentId, @Param("agentDO") AgentDO agentDO);

    Integer deleteAgentById(@Param("agentId") String agentId);
}