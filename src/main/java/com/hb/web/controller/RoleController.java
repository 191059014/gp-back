package com.hb.web.controller;


import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.CloneUtils;
import com.hb.web.api.IRoleService;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import com.hb.web.model.RoleDO;
import com.hb.web.vo.webvo.response.RoleQueryResponseVO;
import com.hb.web.vo.webvo.response.RoleTreeResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public ResponseData<List<RoleQueryResponseVO>> getRoleListPage(@RequestBody RoleDO roleDO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<RoleDO> pageList = roleService.findPageList(roleDO, pageNum, pageSize);
        List<RoleQueryResponseVO> resultList = new ArrayList<>();
        for (RoleDO aDo : pageList) {
            RoleQueryResponseVO responseVO = CloneUtils.clone(aDo, RoleQueryResponseVO.class);
            // 根据roleId查询角色对应的权限
            Set<String> permissionSet = roleService.getPermissionByRoleId(aDo.getRoleId());
            responseVO.setPermissionValueSet(permissionSet);
            resultList.add(responseVO);
        }
        Integer count = roleService.findCount(roleDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, resultList, count);
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

    @GetMapping("/findRoleTree")
    public ResponseData<List<RoleTreeResponseVO>> findRoleTree() {
        List<RoleTreeResponseVO> roleTreeList = roleService.findRoleTree();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, roleTreeList);
    }

}
