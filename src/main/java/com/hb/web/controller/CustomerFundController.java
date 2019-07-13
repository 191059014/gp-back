package com.hb.web.controller;

import com.hb.web.api.ICustomerFundService;
import com.hb.web.common.ResponseEnum;
import com.hb.web.model.CustomerFundDO;
import com.hb.web.common.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ========== 客户资金controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.CustomerFundController.java, v1.0
 * @date 2019年06月16日 21时10分
 */
@Api(tags = "[WEB]客户资金信息")
@RestController
@RequestMapping("controller/customerFund")
public class CustomerFundController {

    @Autowired
    private ICustomerFundService iCustomerFundService;

    @ApiOperation(value = "分页条件查询客户资金列表")
    @PostMapping("/getCustomerFundListPage")
    public ResponseData<List<CustomerFundDO>> getCustomerFundListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody CustomerFundDO customerFundDO) {
        List<CustomerFundDO> customerFundList = iCustomerFundService.findCustomerFundList(customerFundDO, pageNum, pageSize);
        Integer count = iCustomerFundService.findCount(customerFundDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, customerFundList, pageNum, count);
    }

    @ApiOperation(value = "添加客户资金信息")
    @PostMapping("/addCustomerFund")
    public ResponseData addCustomerFund(@RequestBody CustomerFundDO customerFundDO) {
        int result = iCustomerFundService.addCustomerFund(customerFundDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询客户资金信息")
    @PostMapping("/findCustomerFund")
    public ResponseData findCustomerFund(@RequestBody CustomerFundDO customerFundDO) {
        CustomerFundDO result = iCustomerFundService.findCustomerFund(customerFundDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改客户资金信息")
    @PostMapping("/updateCustomerFundById")
    public ResponseData updateCustomerFundById(@RequestBody CustomerFundDO customerFundDO) {
        int result = iCustomerFundService.updateByPrimaryKeySelective(customerFundDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除客户资金信息")
    @GetMapping("/deleteCustomerFundById")
    public ResponseData deleteCustomerFundById(String userId) {
        int result = iCustomerFundService.deleteCustomerFundById(userId);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

}
