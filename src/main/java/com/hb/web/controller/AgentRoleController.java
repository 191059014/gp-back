package com.hb.web.controller;

import com.hb.web.api.IAgentRoleService;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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

}
