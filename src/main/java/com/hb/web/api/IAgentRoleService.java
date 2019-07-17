package com.hb.web.api;

import com.hb.web.model.AgentRoleDO;

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

}
