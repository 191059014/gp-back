package com.hb.web.controller;

import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.vo.webvo.request.HoldReportQueryRequestVO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IAlreadySettledReportService;
import com.hb.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ========== 已结算报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentReportController.java, v1.0
 * @date 2019年07月22日 22时19分
 */
@Api(tags = "[WEB]已结算报表")
@RestController
@RequestMapping("controller/alreadySettledReport")
public class AlreadySettledReportController extends BaseController {
    /**
     * the common logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AlreadySettledReportController.class);

    @Autowired
    private IAlreadySettledReportService iAlreadySettledReportService;

    @ApiOperation(value = "已结算报表分页查询")
    @PostMapping("/findAlreadySettledReportPages")
    public ResponseData<List<Map<String, Object>>> findAlreadySettledReportPages(@RequestBody HoldReportQueryRequestVO requestVO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        LOGGER.info("已结算报表分页查询，入参：{}，{}，{}", requestVO, pageNum, pageSize);
        List<Map<String, Object>> result = iAlreadySettledReportService.findHoldReportPages(requestVO, pageNum, pageSize);
        int count = iAlreadySettledReportService.findCount(requestVO);
        LOGGER.info("已结算报表分页查询，返回结果：{}", result);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result, count);
    }

    @ApiOperation(value = "导出已结算报表")
    @PostMapping("/exportAlreadySettledReport")
    public void exportAlreadySettledReport(@RequestBody HoldReportQueryRequestVO requestVO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        LOGGER.info("导出已结算报表，入参：{}，{}，{}", requestVO, pageNum, pageSize);
        iAlreadySettledReportService.exportExcel(response, requestVO, pageNum, pageSize);
        LOGGER.info("导出已结算报表，成功");
    }

}
