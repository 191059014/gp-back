package com.hb.web.controller;

import com.hb.facade.entity.UserDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IUserReportService;
import com.hb.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ========== 客户报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentReportController.java, v1.0
 * @date 2019年07月22日 22时19分
 */
@Api(tags = "[WEB]客户报表")
@RestController
@RequestMapping("controller/userReport")
public class UserReportController extends BaseController {
    /**
     * the common logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserReportController.class);

    @Autowired
    private IUserReportService iUserReportService;

    @ApiOperation(value = "导出客户报表")
    @PostMapping("/exportUserReport")
    public void exportUserReport(@RequestBody UserDO userDO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        LOGGER.info("导出客户报表，入参：{}，{}，{}", userDO, pageNum, pageSize);
        iUserReportService.exportExcel(response, userDO, pageNum, pageSize);
        LOGGER.info("导出客户报表，成功");
    }

}
