package com.hb.web.controller;


import com.hb.web.api.IRoleService;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ========== 角色controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.RoleController.java, v1.0
 * @date 2019年06月07日 23时02分
 */
@ApiIgnore
@RestController
@RequestMapping("controller/role")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private IRoleService roleService;

}
