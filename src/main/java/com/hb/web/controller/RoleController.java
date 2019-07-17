package com.hb.web.controller;


import com.hb.web.api.IRoleService;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import com.hb.web.model.RoleDO;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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

    @RequestMapping("/getRoleListPage")
    public ResponseData<List<RoleDO>> getRoleListPage(@RequestBody RoleDO roleDO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<RoleDO> pageList = roleService.findPageList(roleDO, pageNum, pageSize);
        Integer count = roleService.findCount(roleDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, pageList, count);
    }

    @RequestMapping("/addRole")
    public ResponseData addRole(@RequestBody RoleDO roleDO) {
        int i = roleService.addRole(roleDO);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

    @RequestMapping("/updateRole")
    public ResponseData updateRole(@RequestBody RoleDO roleDO) {
        int i = roleService.updateByPrimaryKeySelective(roleDO);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

    @RequestMapping("/deleteRoleById")
    public ResponseData deleteRoleById(Integer roleId) {
        int i = roleService.deleteByPrimaryKey(roleId);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

}
