package com.hb.web.controller;

import com.hb.facade.entity.UserDO;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.api.IUserService;
import com.hb.web.base.BaseController;
import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 用户controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.sys.UserController.java, v1.0
 * @date 2019年06月03日 21时23分
 */
@RestController
@RequestMapping("controller/user")
@Api(tags = "[WEB]客户")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value = "分页条件查询用户列表")
    @PostMapping("/getUserListPage")
    public ResponseData<List<UserDO>> getUserListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody UserDO userDO) {
        List<UserDO> userList = iUserService.findUserList(userDO, pageNum, pageSize);
        Integer count = iUserService.findCount(userDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, userList, count);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public ResponseData addUser(@RequestBody UserDO userDO) {
        boolean result = iUserService.addUser(userDO);
        if (result) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询用户")
    @PostMapping("/findUser")
    public ResponseData findUser(@RequestBody UserDO userDO) {
        UserDO result = iUserService.findUser(userDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/updateUser")
    public ResponseData updateUser(@RequestBody UserDO userDO) {
        if (StringUtils.isNotBlank(userDO.getPassword())) {
            userDO.setPassword(EncryptUtils.encode(userDO.getPassword()));
        }
        boolean result = iUserService.updateUserById(userDO.getUserId(), userDO);
        if (result) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除用户")
    @GetMapping("/deleteUserById")
    public ResponseData deleteUserById(String userId) {
        boolean result = iUserService.deleteUserById(userId);
        if (result) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "获取实名认证状态下拉框")
    @GetMapping("/getRealAuthStatusCombobox")
    public ResponseData<List<Map<String, Object>>> getRealAuthStatusCombobox() {
        List<Map<String, Object>> result = iUserService.getRealAuthStatusList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "查询用户对应权限集合")
    @GetMapping("/getUserPermissionList")
    public ResponseData<Set<String>> getUserPermissionList(String userId) {
        Set<String> result = iUserService.getUserPermissionList(userId);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

}
