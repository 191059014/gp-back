package com.hb.web.impl;

import com.hb.web.api.IAgentFundService;
import com.hb.web.model.AgentFundDO;
import com.hb.web.mapper.AgentFundMapper;
import com.hb.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ========== 代理商资金管理service实现类 ==========
 *
 * @author Mr.huang
 * @version AgentFundServiceImpl.java, v1.0
 * @date 2019年06月13日 13时43分
 */
@Service
public class AgentFundServiceImpl implements IAgentFundService {

    @Value("${gpweb.unit}")
    private Integer unit;

    @Autowired
    private AgentFundMapper agentFundMapper;

    @Override
    public List<AgentFundDO> findAgentFundList(AgentFundDO agentFundDO, Integer pageNum, Integer pageSize) {
        return agentFundMapper.findAgentFundList(agentFundDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(AgentFundDO agentFundDO) {
        return agentFundMapper.findCount(agentFundDO);
    }

    @Override
    public int addAgentFund(AgentFundDO agentFundDO) {
        agentFundDO.setUnit(unit);
        return agentFundMapper.insertSelective(agentFundDO);
    }

    @Override
    public AgentFundDO findAgentFund(AgentFundDO agentFundDO) {
        return null;
    }

    @Override
    public int updateAgentFundById(String agentId, AgentFundDO agentFundDO) {
        return 0;
    }

    @Override
    public int deleteAgentFundById(String agentId) {
        return 0;
    }

}
