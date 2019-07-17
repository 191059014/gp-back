package com.hb.web.impl;

import com.hb.web.api.IAgentRoleService;
import com.hb.web.mapper.AgentRoleMapper;
import com.hb.web.model.AgentRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ========== 代理商-角色 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentRoleServiceImpl.java, v1.0
 * @date 2019年07月17日 11时12分
 */
@Service
public class AgentRoleServiceImpl implements IAgentRoleService {

    @Autowired
    private AgentRoleMapper agentRoleMapper;

    @Override
    public int addAgentRole(AgentRoleDO agentRoleDO) {
        return agentRoleMapper.insertSelective(agentRoleDO);
    }

}
