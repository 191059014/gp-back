package com.hb.web.impl;

import com.hb.web.api.IAgentRoleService;
import com.hb.web.mapper.AgentRoleMapper;
import com.hb.web.model.AgentRoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean batchInsert(String agentId, Set<Integer> roleIdSet) {
        int i = agentRoleMapper.deleteByAgentId(agentId);
        List<AgentRoleDO> addList = new ArrayList<>();
        for (Integer roleId : roleIdSet) {
            addList.add(new AgentRoleDO(agentId, roleId));
        }
        int j = agentRoleMapper.batchInsert(addList);
        return true;
    }

    @Override
    public Set<Integer> getRoleIdSetByAgentId(String agentId) {
        return agentRoleMapper.getRoleIdSetByAgentId(agentId);
    }

}
