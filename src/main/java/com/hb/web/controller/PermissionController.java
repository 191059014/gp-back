package com.hb.web.controller;

import com.hb.web.api.IPermissionService;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import com.hb.web.model.PermissionDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

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

    @RequestMapping("/getPermissionListPage")
    public ResponseData<List<PermissionDO>> getPermissionListPage(@RequestBody PermissionDO permissionDO, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        List<PermissionDO> pageList = iPermissionService.findPageList(permissionDO, pageNum, pageSize);
        Integer count = iPermissionService.findCount(permissionDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, pageList, count);
    }

    @RequestMapping("/addPermission")
    public ResponseData addPermission(@RequestBody PermissionDO permissionDO) {
        int i = iPermissionService.addPermission(permissionDO);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

    @RequestMapping("/updatePermission")
    public ResponseData updatePermission(@RequestBody PermissionDO permissionDO) {
        int i = iPermissionService.updateByPrimaryKeySelective(permissionDO);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

    @RequestMapping("/deletePermissionById")
    public ResponseData deletePermissionById(Integer permissionId) {
        int i = iPermissionService.deleteByPrimaryKey(permissionId);
        if (i < 1) {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

    @GetMapping("/getSourceTypeCombobox")
    public ResponseData<List<Map<String, Object>>> getSourceTypeCombobox() {
        List<Map<String, Object>> result = iPermissionService.getSourceTypeList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @PostMapping("/batchInsert")
    public ResponseData batchInsert(@RequestBody List<PermissionDO> permissionList){
        boolean result = iPermissionService.batchInsert(permissionList);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
    }

}
