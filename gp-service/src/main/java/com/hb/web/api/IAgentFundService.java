package com.hb.web.api;


import com.hb.facade.entity.AgentFundDO;

import java.util.List;

/**
 * ========== 代理商资金管理service ==========
 *
 * @author Mr.huang
 * @version IAgentFundService.java, v1.0
 * @date 2019年06月13日 13时43分
 */
public interface IAgentFundService {

    /**
     * ########## 条件查询代理商资金信息 ##########
     *
     * @param agentFundDO 代理商资金信息
     * @param pageNum     当前页数
     * @param pageSize    每页条数
     * @return 代理商资金信息集合
     */
    List<AgentFundDO> findAgentFundList(AgentFundDO agentFundDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 条件查询代理商资金信息总条数 ##########
     *
     * @param agentFundDO 代理商资金信息
     * @return 总条数
     */
    Integer findCount(AgentFundDO agentFundDO);

    /**
     * ########## 添加代理商资金信息 ##########
     *
     * @param agentFundDO 代理商资金信息
     * @return int
     */
    int addAgentFund(AgentFundDO agentFundDO);

    /**
     * ########## 查找代理商资金信息 ##########
     *
     * @param agentFundDO 代理商资金信息
     * @return AgentFundDO
     */
    AgentFundDO findAgentFund(AgentFundDO agentFundDO);

    /**
     * ########## 更新代理商资金信息 ##########
     *
     * @param agentId     代理商ID
     * @param agentFundDO 代理商资金信息
     * @return int
     */
    int updateAgentFundById(String agentId, AgentFundDO agentFundDO);

    /**
     * ########## 删除代理商资金信息 ##########
     *
     * @param agentId 代理商ID
     * @return int
     */
    int deleteAgentFundById(String agentId);
}
