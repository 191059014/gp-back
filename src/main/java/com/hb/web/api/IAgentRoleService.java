package com.hb.web.api;

import com.hb.entity.AgentRoleDO;

import java.util.Set;

/**
 * ========== 代理商-角色 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IAgentRoleService.java, v1.0
 * @date 2019年07月17日 09时39分
 */
public interface IAgentRoleService {

    /**
     * ########## 添加代理商角色关系 ##########
     *
     * @param agentRoleDO 代理商角色关系信息
     * @return 是否成功
     */
    int addAgentRole(AgentRoleDO agentRoleDO);

    /**
     * ########## 批量新增代理商角色关系 ##########
     *
     * @param agentId   代理商ID
     * @param roleIdSet 角色id集合
     * @return 是否成功
     */
    boolean batchInsert(String agentId, Set<Integer> roleIdSet);

    /**
     * ########## 通过代理商id查询角色集合 ##########
     *
     * @param agentId 代理商ID
     * @return 角色ID集合
     */
    Set<Integer> getRoleIdSetByAgentId(String agentId);
}
