package com.hb.web.controller;

import com.hb.web.api.IRolePermissionService;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * ========== 角色权限controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.RolePermissionController.java, v1.0
 * @date 2019年07月17日 11时32分
 */
@ApiIgnore
@RestController
@RequestMapping("controller/rolePermission")
public class RolePermissionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionController.class);

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @RequestMapping("/batchInsert")
    public ResponseData batchInsert(@RequestBody List<String> permissionValueList, @RequestParam("roleId") Integer roleId) {
        if (CollectionUtils.isEmpty(permissionValueList) || roleId == null) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR_PARAM_VERIFY);
        }
        int i = iRolePermissionService.batchInsert(permissionValueList, roleId);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

}
