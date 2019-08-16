package com.hb.web.controller;

import com.hb.facade.entity.AgentDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IAgentReportService;
import com.hb.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ========== 代理商报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentReportController.java, v1.0
 * @date 2019年07月22日 22时19分
 */
@Api(tags = "[WEB]代理商报表")
@RestController
@RequestMapping("controller/agentReport")
public class AgentReportController extends BaseController {
    /**
     * the common logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentReportController.class);

    @Autowired
    private IAgentReportService iAgentReportService;

    @ApiOperation(value = "导出代理商报表")
    @PostMapping("/exportAgentReport")
    public void exportAgentReport(@RequestBody AgentDO agentDO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        LOGGER.info("导出代理商报表，入参：{}，{}，{}", agentDO, pageNum, pageSize);
        iAgentReportService.exportExcel(response, agentDO, pageNum, pageSize);
        LOGGER.info("导出代理商报表，成功");
    }

}
