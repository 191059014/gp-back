package com.hb.web.controller;

import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.vo.webvo.request.HoldReportQueryRequestVO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IHolddingReportService;
import com.hb.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ========== 持仓中报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentReportController.java, v1.0
 * @date 2019年07月22日 22时19分
 */
@Api(tags = "[WEB]持仓中报表")
@RestController
@RequestMapping("controller/holdReport")
public class HoldReportController extends BaseController {
    /**
     * the common logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(HoldReportController.class);

    @Autowired
    private IHolddingReportService iHolddingReportService;

    @ApiOperation(value = "持仓中报表分页查询")
    @PostMapping("/findHoldReportPages")
    public ResponseData<List<Map<String, Object>>> findHoldReportPages(@RequestBody HoldReportQueryRequestVO requestVO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        LOGGER.info("持仓中报表分页查询，入参：{}，{}，{}", requestVO, pageNum, pageSize);
        List<Map<String, Object>> result = iHolddingReportService.findHoldReportPages(requestVO, pageNum, pageSize);
        int count = iHolddingReportService.findCount(requestVO);
        LOGGER.info("持仓中报表分页查询，返回结果：{}", result);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result, count);
    }

    @ApiOperation(value = "导出持仓中报表")
    @PostMapping("/exportHoldReport")
    public void exportHoldReport(@RequestBody HoldReportQueryRequestVO requestVO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        LOGGER.info("导出持仓中报表，入参：{}，{}，{}", requestVO, pageNum, pageSize);
        iHolddingReportService.exportExcel(response, requestVO, pageNum, pageSize);
        LOGGER.info("导出持仓中报表，成功");
    }

}
