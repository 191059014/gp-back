package com.hb.web.api;

import com.hb.facade.entity.AgentDO;

import javax.servlet.http.HttpServletResponse;

/**
 * ========== 客户报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.ICustomerReportService.java, v1.0
 * @date 2019年07月16日 22时23分
 */
public interface ICustomerReportService {
    /**
     * ########## 导出excel ##########
     *
     * @param response 响应对象
     * @param agentDO  查询条件
     */
    void exportExcel(HttpServletResponse response, AgentDO agentDO);
}
