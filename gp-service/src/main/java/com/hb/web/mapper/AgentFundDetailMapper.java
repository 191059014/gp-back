package com.hb.web.mapper;

import com.hb.facade.entity.AgentFundDeailDO;
import com.hb.web.vo.webvo.request.AgentFundDetailRequestVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentFundDetailMapper {

    int insertSelective(AgentFundDeailDO record);

    List<AgentFundDeailDO> findAgentFundDetailList(@Param("agentFundDetailRequestVO") AgentFundDetailRequestVO agentFundDetailRequestVO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("agentFundDetailRequestVO") AgentFundDetailRequestVO agentFundDetailRequestVO);
}