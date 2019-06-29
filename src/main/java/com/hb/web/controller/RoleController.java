package com.hb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * ========== 角色controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.RoleController.java, v1.0
 * @date 2019年06月07日 23时02分
 */
@ApiIgnore
@Controller
@RequestMapping("controller/role")
public class RoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @GetMapping("/toIndex")
    public String toIndex() {
        return "sys/role_manage";
    }

}
