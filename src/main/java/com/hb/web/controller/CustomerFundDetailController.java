package com.hb.web.controller;

import com.hb.web.api.ICustomerFundDetailService;
import com.hb.web.model.ResponseEnum;
import com.hb.web.model.CustomerFundDetailDO;
import com.hb.web.model.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ========== 客户资金流水controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.CustomerFundDetailController.java, v1.0
 * @date 2019年06月17日 14时33分
 */
@Api(tags = "[WEB]客户资金流水信息")
@RestController
@RequestMapping("controller/customerFundDetail")
public class CustomerFundDetailController {

    @Autowired
    private ICustomerFundDetailService iCustomerFundDetailService;

    @ApiOperation(value = "分页条件查询客户资金流水信息列表")
    @PostMapping("/getCustomerFundDetailListPage")
    public ResponseData<List<CustomerFundDetailDO>> getCustomerFundDetailListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody CustomerFundDetailDO customerFundDetailDO) {
        List<CustomerFundDetailDO> customerFundDetailList = iCustomerFundDetailService.findListByCondition(customerFundDetailDO, pageNum, pageSize);
        Integer count = iCustomerFundDetailService.findCountByCondition(customerFundDetailDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, customerFundDetailList, pageNum, count);
    }

    @ApiOperation(value = "添加客户资金流水信息")
    @PostMapping("/addCustomerFundDetail")
    public ResponseData addCustomerFundDetail(@RequestBody CustomerFundDetailDO customerFundDetailDO) {
        int result = iCustomerFundDetailService.addOne(customerFundDetailDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询客户资金流水信息")
    @PostMapping("/findCustomerFundDetail")
    public ResponseData findCustomerFundDetail(@RequestBody CustomerFundDetailDO customerFundDetailDO) {
        CustomerFundDetailDO result = iCustomerFundDetailService.findOne(customerFundDetailDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改客户资金流水信息")
    @PostMapping("/updateCustomerFundDetailById")
    public ResponseData updateCustomerFundDetailById(@RequestBody CustomerFundDetailDO customerFundDetailDO) {
        int result = iCustomerFundDetailService.updateByPrimaryKeySelective(customerFundDetailDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除客户资金流水信息")
    @GetMapping("/deleteCustomerFundDetailById")
    public ResponseData deleteCustomerFundDetailById(String detailId) {
        int result = iCustomerFundDetailService.deleteById(detailId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

}
