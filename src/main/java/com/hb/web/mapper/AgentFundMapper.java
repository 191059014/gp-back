package com.hb.web.mapper;

import com.hb.web.model.AgentFundDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentFundMapper {

    int insert(AgentFundDO record);

    int insertSelective(AgentFundDO record);

    List<AgentFundDO> findAgentFundList(@Param("agentFundDO") AgentFundDO agentFundDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("agentFundDO") AgentFundDO agentFundDO);
}