package com.hb.web.controller;

import com.hb.facade.entity.AgentDO;
import com.hb.unic.util.util.CloneUtils;
import com.hb.web.api.IAgentRoleService;
import com.hb.web.api.IAgentService;
import com.hb.web.base.BaseController;
import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.vo.webvo.response.AgentQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 代理商controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.AgentController.java, v1.0
 * @date 2019年06月16日 12时30分
 */
@Api(tags = "[WEB]代理商")
@RestController
@RequestMapping("controller/agent")
public class AgentController extends BaseController {

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private IAgentRoleService iAgentRoleService;

    @ApiOperation(value = "分页条件查询代理商列表")
    @PostMapping("/getAgentListPage")
    public ResponseData<List<AgentQueryResponseVO>> getAgentListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody AgentDO agentDO) {
        List<AgentDO> agentList = iAgentService.findAgentList(agentDO, pageNum, pageSize);
        Integer count = iAgentService.findCount(agentDO);
        List<AgentQueryResponseVO> resultList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(agentList)) {
            for (AgentDO agent : agentList) {
                AgentQueryResponseVO clone = CloneUtils.clone(agent, AgentQueryResponseVO.class);
                Set<Integer> roleIdSet = iAgentRoleService.getRoleIdSetByAgentId(agent.getAgentId());
                clone.setRoleIdSet(roleIdSet);
                resultList.add(clone);
            }
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, resultList, count);
    }

    @ApiOperation(value = "添加代理商")
    @PostMapping("/addAgent")
    public ResponseData addAgent(@RequestBody AgentDO agentDO) {
        AgentDO query = new AgentDO();
        query.setMobile(agentDO.getMobile());
        AgentDO queryAgent = iAgentService.findAgent(query);
        if (queryAgent != null) {
            return ResponseData.generateResponseData(ResponseEnum.MOBILE_ALREADY_EXIST);
        }
        int result = iAgentService.addAgent(agentDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询代理商")
    @PostMapping("/findAgent")
    public ResponseData findAgent(@RequestBody AgentDO agentDO) {
        AgentDO result = iAgentService.findAgent(agentDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改代理商")
    @PostMapping("/updateAgent")
    public ResponseData updateAgent(@RequestBody AgentDO agentDO) {
        int result = iAgentService.updateAgentById(agentDO.getAgentId(), agentDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除代理商")
    @GetMapping("/deleteAgentById")
    public ResponseData deleteAgentById(String agentId) {
        int result = iAgentService.deleteAgentById(agentId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "获取代理等级下拉框")
    @GetMapping("/getAgentLevelCombobox")
    public ResponseData<List<Map<String, Object>>> getAgentLevelCombobox() {
        List<Map<String, Object>> result = iAgentService.getAgentLevelList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

}
