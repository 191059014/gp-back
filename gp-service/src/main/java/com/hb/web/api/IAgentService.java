package com.hb.web.api;

import com.hb.facade.entity.AgentDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 代理商service接口 ==========
 *
 * @author Mr.huang
 * @version IAgentService.java, v1.0
 * @date 2019年06月16日 12时24分
 */
public interface IAgentService {

    /**
     * ########## 获取所有代理类型 ##########
     *
     * @return 代理类型集合
     */
    List<Map<String, Object>> getAgentLevelList();

    /**
     * ########## 查找代理商集合 ##########
     *
     * @param agentDO  代理商信息查询条件
     * @param pageNum  当前第几页
     * @param pageSize 每页多少条
     * @return List<AgentDO>
     */
    List<AgentDO> findAgentList(AgentDO agentDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 查找代理商集合总条数 ##########
     *
     * @param agentDO 代理商信息查询条件
     * @return Integer
     */
    Integer findCount(AgentDO agentDO);

    /**
     * ########## 添加代理商 ##########
     *
     * @param agentDO 代理商信息
     * @return Integer
     */
    Integer addAgent(AgentDO agentDO);

    /**
     * ########## 查找代理商 ##########
     *
     * @param agentDO 代理商信息
     * @return AgentDO
     */
    AgentDO findAgent(AgentDO agentDO);

    /**
     * ########## 更新代理商 ##########
     *
     * @param agentId 代理商ID
     * @param agentDO 代理商信息
     * @return Integer
     */
    Integer updateAgentById(String agentId, AgentDO agentDO);

    /**
     * ########## 删除代理商 ##########
     *
     * @param agentId 代理商ID
     * @return Integer
     */
    Integer deleteAgentById(String agentId);

    /**
     * ########## 查询代理商的权限 ##########
     *
     * @param agentId 代理商id
     * @return 权限集合
     */
    Set<String> getPermissionSet(String agentId);

    /**
     * 根据邀请人手机号查询代理人员
     *
     * @param inviterMobile 邀请人手机号
     * @return 代理人信息
     */
    AgentDO getAgentByInviterMobile(String inviterMobile);
}
