package com.hb.web.controller;

import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.entity.OfflinePayChekDO;
import com.hb.facade.entity.UserDO;
import com.hb.facade.vo.webvo.response.OfflinePayCheckResponseVO;
import com.hb.unic.util.util.CloneUtils;
import com.hb.web.api.IOfflinePayService;
import com.hb.web.api.IUserService;
import com.hb.web.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ========== 线下支付 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.OfflinePayController.java, v1.0
 * @date 2019年06月12日 15时18分
 */
@Api(tags = "[WEB]线下支付")
@RestController
@RequestMapping("controller/offlinePay")
public class OfflinePayController {

    @Autowired
    private IOfflinePayService iOfflinePayService;

    @Autowired
    private IUserService iUserService;

    /**
     * ########## 获取审核状态下拉框 ##########
     *
     * @return 支付状态集合
     */
    @ApiOperation(value = "获取审核状态下拉框")
    @GetMapping("/getOfflineCheckStatusCombobox")
    public ResponseData<List<Map<String, Object>>> getOfflineCheckStatusCombobox() {
        List<Map<String, Object>> mapList = iOfflinePayService.getOfflineCheckStatusList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, mapList);
    }

    /**
     * ########## 获取支付状态下拉框 ##########
     *
     * @return 支付状态集合
     */
    @ApiOperation(value = "获取审核状态下拉框")
    @GetMapping("/getOfflinePayStatusCombobox")
    public ResponseData<List<Map<String, Object>>> getOfflinePayStatusCombobox() {
        List<Map<String, Object>> mapList = iOfflinePayService.getOfflinePayStatusCombobox();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, mapList);
    }

    /**
     * ########## 获取支付渠道下拉框 ##########
     *
     * @return 支付类型下拉框
     */
    @ApiOperation(value = "获取支付渠道下拉框")
    @GetMapping("/getOfflinePayChannelCombobox")
    public ResponseData<List<Map<String, Object>>> getOfflinePayChannelCombobox() {
        List<Map<String, Object>> mapList = iOfflinePayService.getOfflinePayChannelList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, mapList);
    }

    /**
     * ########## 分页查询线下支付信息列表 ##########
     *
     * @param pageNum          当前第几页
     * @param pageSize         每页条数
     * @param offlinePayChekDO 线下支付审核实体
     * @return 分页结果
     */
    @ApiOperation(value = "分页查询线下支付信息列表")
    @PostMapping("/getOfflinePayListPage")
    public ResponseData<List<OfflinePayCheckResponseVO>> getOfflinePayListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody OfflinePayChekDO offlinePayChekDO) {
        List<OfflinePayCheckResponseVO> resultList = new ArrayList<>();
        List<OfflinePayChekDO> list = iOfflinePayService.findList(offlinePayChekDO, pageNum, pageSize);
        Integer count = iOfflinePayService.findCount(offlinePayChekDO);
        if (CollectionUtils.isNotEmpty(list)) {
            Set<String> userIdSet = list.stream().map(OfflinePayChekDO::getUserId).collect(Collectors.toSet());
            Map<String, UserDO> userMap = iUserService.getUserMapByUserIdSet(userIdSet);
            for (OfflinePayChekDO payChekDO : list) {
                OfflinePayCheckResponseVO clone = CloneUtils.clone(payChekDO, OfflinePayCheckResponseVO.class);
                clone.setUserName(userMap.get(payChekDO.getUserId()).getUserName());
                clone.setMobile(userMap.get(payChekDO.getUserId()).getMobile());
                resultList.add(clone);
            }
        }
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, resultList, count);
    }

    @ApiOperation(value = "添加线下审核信息")
    @PostMapping("/addOne")
    public ResponseData addOne(@RequestBody OfflinePayChekDO offlinePayChekDO) {
        int result = iOfflinePayService.addOne(offlinePayChekDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询线下审核信息")
    @PostMapping("/findOne")
    public ResponseData findOne(@RequestBody OfflinePayChekDO offlinePayChekDO) {
        OfflinePayChekDO result = iOfflinePayService.findOne(offlinePayChekDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改线下审核信息")
    @PostMapping("/update")
    public ResponseData update(@RequestBody OfflinePayChekDO offlinePayChekDO) {
        try {
            iOfflinePayService.update(offlinePayChekDO);
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } catch (BusinessException e) {
            return ResponseData.generateResponseData(e.getCode(), e.getMsg());
        }
    }

    @ApiOperation(value = "删除线下审核信息")
    @GetMapping("/deleteById")
    public ResponseData deleteById(Integer checkId) {
        int result = iOfflinePayService.deleteById(checkId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

}
