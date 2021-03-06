package com.hb.web.api;

import com.hb.facade.entity.AgentDO;

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
     * @param pageNum  当前页数
     * @param pageSize 每页条数
     */
    void exportExcel(HttpServletResponse response, AgentDO agentDO, Integer pageNum, Integer pageSize);
}
