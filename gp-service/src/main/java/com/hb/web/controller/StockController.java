package com.hb.web.controller;

import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.entity.StockListDO;
import com.hb.web.api.IStockListService;
import com.hb.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ========== 股票controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.sys.UserController.java, v1.0
 * @date 2019年06月03日 21时23分
 */
@RestController
@RequestMapping("controller/stock")
@Api(tags = "[WEB]股票")
public class StockController extends BaseController {

    @Autowired
    private IStockListService iStockListService;

    @ApiOperation(value = "分页条件查询股票列表")
    @PostMapping("/getStockListPage")
    public ResponseData<List<StockListDO>> getStockListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody StockListDO stockListDO) {
        List<StockListDO> stockList = iStockListService.findStockList(stockListDO, pageNum, pageSize);
        Integer count = iStockListService.findCount(stockListDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, stockList, count);
    }

    @ApiOperation(value = "添加股票")
    @PostMapping("/addStock")
    public ResponseData addStock(@RequestBody StockListDO stockListDO) {
        int updateRecord = iStockListService.addStock(stockListDO);
        if (updateRecord > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询股票")
    @PostMapping("/findStock")
    public ResponseData findStock(@RequestBody StockListDO stockListDO) {
        StockListDO result = iStockListService.findStock(stockListDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

    @ApiOperation(value = "修改股票")
    @PostMapping("/updateStock")
    public ResponseData updateStock(@RequestBody StockListDO stockListDO) {
        int updateRecord = iStockListService.updateStockById(stockListDO);
        if (updateRecord > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "删除股票")
    @GetMapping("/deleteStockById")
    public ResponseData deleteStockById(Integer id) {
        int updateRecord = iStockListService.deleteStockById(id);
        if (updateRecord > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "获取股票状态下拉框")
    @GetMapping("/getStockStatusCombobox")
    public ResponseData<List<Map<String, Object>>> getStockStatusCombobox() {
        List<Map<String, Object>> result = iStockListService.getStockStatusList();
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, result);
    }

}
