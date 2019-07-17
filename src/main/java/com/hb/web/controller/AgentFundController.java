package com.hb.web.controller;

import com.hb.web.api.IAgentFundService;
import com.hb.web.common.ResponseEnum;
import com.hb.web.model.AgentFundDO;
import com.hb.web.common.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ========== 代理商资金管理 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.AgentFundController.java, v1.0
 * @date 2019年06月13日 13时41分
 */
@RestController
@RequestMapping("controller/agentFund")
@Api(tags = "[WEB]代理商资金")
public class AgentFundController {

    @Autowired
    private IAgentFundService iAgentFundService;

    @ApiOperation(value = "分页条件查询代理商资金列表")
    @PostMapping("/getAgentListPage")
    public ResponseData<List<AgentFundDO>> getAgentFundListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody AgentFundDO agentFundDO) {
        List<AgentFundDO> agentFundList = iAgentFundService.findAgentFundList(agentFundDO, pageNum, pageSize);
        Integer count = iAgentFundService.findCount(agentFundDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, agentFundList, count);
    }

    @ApiOperation(value = "添加代理商资金信息")
    @PostMapping("/addAgentFund")
    public ResponseData addAgentFund(@RequestBody AgentFundDO agentFundDO) {
        int result = iAgentFundService.addAgentFund(agentFundDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询代理商资金信息")
    @PostMapping("/findAgentFund")
    public ResponseData findAgentFund(@RequestBody AgentFundDO agentFundDO) {
        AgentFundDO result = iAgentFundService.findAgentFund(agentFundDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改代理商资金信息")
    @PostMapping("/updateAgentFundById")
    public ResponseData updateAgentFundById(@RequestBody AgentFundDO agentFundDO) {
        int result = iAgentFundService.updateAgentFundById(agentFundDO.getAgentId(), agentFundDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除代理商资金信息")
    @GetMapping("/deleteAgentFundById")
    public ResponseData deleteAgentFundById(String agentId) {
        int result = iAgentFundService.deleteAgentFundById(agentId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

}
