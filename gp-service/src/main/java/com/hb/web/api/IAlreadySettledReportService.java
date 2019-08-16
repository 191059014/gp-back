package com.hb.web.api;

import com.hb.facade.entity.AgentDO;

import javax.servlet.http.HttpServletResponse;

/**
 * ========== 已结算报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IAlreadySettledReportService.java, v1.0
 * @date 2019年07月16日 22时17分
 */
public interface IAlreadySettledReportService {
    /**
     * ########## 导出报表 ##########
     *
     * @param response 响应对象
     */
    void exportExcel(HttpServletResponse response, AgentDO agentDO);
}
