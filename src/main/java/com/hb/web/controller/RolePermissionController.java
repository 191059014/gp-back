package com.hb.web.controller;

import com.hb.web.api.IRolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ========== 角色权限controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.RolePermissionController.java, v1.0
 * @date 2019年07月17日 11时32分
 */
@ApiIgnore
@RestController
@RequestMapping("controller/rolePermission")
public class RolePermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionController.class);

    @Autowired
    private IRolePermissionService iRolePermissionService;

}
