package com.hb.web.api;


import com.hb.facade.entity.AgentDO;

import javax.servlet.http.HttpServletResponse;

/**
 * ========== 运营报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IOperationReportService.java, v1.0
 * @date 2019年07月16日 22时15分
 */
public interface IOperationReportService {
    /**
     * ########## 导出报表 ##########
     *
     * @param response 响应对象
     */
    void exportExcel(HttpServletResponse response, AgentDO agentDO);
}
