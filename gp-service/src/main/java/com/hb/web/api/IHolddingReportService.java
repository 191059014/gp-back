package com.hb.web.api;

import com.hb.facade.entity.AgentDO;

import javax.servlet.http.HttpServletResponse;

/**
 * ========== 持仓中报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IHolddingReportService.java, v1.0
 * @date 2019年07月16日 22时20分
 */
public interface IHolddingReportService {
    /**
     * ########## 导出excel ##########
     *
     * @param response 响应对象
     * @param agentDO  查询条件
     */
    void exportExcel(HttpServletResponse response, AgentDO agentDO);
}
