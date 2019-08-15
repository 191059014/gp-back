package com.hb.web.controller;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IAgentRoleService;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Set;

/**
 * ========== 代理商角色controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.AgentRoleController.java, v1.0
 * @date 2019年07月17日 11时28分
 */
@ApiIgnore
@RestController
@RequestMapping("controller/agentRole")
public class AgentRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgentRoleController.class);

    @Autowired
    private IAgentRoleService iAgentRoleService;

    @PostMapping("/batchInsert")
    private ResponseData batchInsert(@RequestBody Set<Integer> roleIdSet, @RequestParam("agentId") String agentId) {
        if (StringUtils.isBlank(agentId)) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR_PARAM_VERIFY);
        }
        boolean result = iAgentRoleService.batchInsert(agentId, roleIdSet);
        if (!result) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

}
