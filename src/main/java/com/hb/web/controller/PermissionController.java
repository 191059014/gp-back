package com.hb.web.controller;

import com.hb.web.api.IPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ========== 权限controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.RoleController.java, v1.0
 * @date 2019年06月07日 23时02分
 */
@ApiIgnore
@RestController
@RequestMapping("controller/permission")
public class PermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private IPermissionService iPermissionService;

}
