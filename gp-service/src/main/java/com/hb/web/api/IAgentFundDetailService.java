package com.hb.web.api;


import com.hb.facade.entity.AgentFundDeailDO;
import com.hb.facade.vo.webvo.request.AgentFundDetailRequestVO;

import java.util.List;
import java.util.Map;

/**
 * ========== 代理商资金流水service接口 ==========
 *
 * @author Mr.huang
 * @version IAgentFundDetailService.java, v1.0
 * @date 2019年06月13日 15时30分
 */
public interface IAgentFundDetailService {

    /**
     * ########## 条件查询代理商资金流水信息 ##########
     *
     * @param agentFundDetailRequestVO 代理商资金信息
     * @param pageNum                  当前页数
     * @param pageSize                 每页条数
     * @return 代理商资金流水集合
     */
    List<AgentFundDeailDO> findAgentFundDetailList(AgentFundDetailRequestVO agentFundDetailRequestVO, Integer pageNum, Integer pageSize);

    /**
     * ########## 条件查询代理商资金流水信息总条数 ##########
     *
     * @param agentFundDetailRequestVO 代理商资金信息
     * @return 总条数
     */
    Integer findCount(AgentFundDetailRequestVO agentFundDetailRequestVO);

    /**
     * ########## 获取所有资金类型 ##########
     *
     * @return 资金类型集合
     */
    List<Map<String,Object>> getFundTypeList();

}
