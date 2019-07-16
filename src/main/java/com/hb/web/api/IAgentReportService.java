package com.hb.web.api;

import com.hb.web.model.AgentDO;

import javax.servlet.http.HttpServletResponse;

/**
 * ========== 代理商报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IAgentReportService.java, v1.0
 * @date 2019年07月16日 22时21分
 */
public interface IAgentReportService {
    /**
     * ########## 导出excel ##########
     *
     * @param response 响应对象
     * @param agentDO  查询条件
     */
    void exportExcel(HttpServletResponse response, AgentDO agentDO);
}
