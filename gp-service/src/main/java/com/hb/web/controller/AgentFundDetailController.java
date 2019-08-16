package com.hb.web.controller;

import com.hb.facade.entity.AgentFundDeailDO;
import com.hb.web.vo.webvo.request.AgentFundDetailRequestVO;
import com.hb.web.api.IAgentFundDetailService;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ========== 代理商资金流水 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentFundSerialController.java, v1.0
 * @date 2019年06月13日 15时21分
 */
@Api(tags = "[WEB]代理商资金流水")
@RestController
@RequestMapping("controller/agentFundDetail")
public class AgentFundDetailController {

    @Autowired
    private IAgentFundDetailService iAgentFundDetailService;

    @ApiOperation(value = "分页查询代理商资金流水列表")
    @PostMapping("/getAgentFundDetailListPage")
    public ResponseData<List<AgentFundDeailDO>> getAgentFundDetailListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody AgentFundDetailRequestVO agentFundDetailRequestVO) {
        List<AgentFundDeailDO> agentFundDetailList = iAgentFundDetailService.findAgentFundDetailList(agentFundDetailRequestVO, pageNum, pageSize);
        Integer count = iAgentFundDetailService.findCount(agentFundDetailRequestVO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, agentFundDetailList, count);
    }

    @ApiOperation(value = "获取资金类型下拉框")
    @GetMapping("/getFundTypeCombobox")
    public ResponseData<List<Map<String, Object>>> getFundTypeCombobox() {
        List<Map<String, Object>> result = iAgentFundDetailService.getFundTypeList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

}
