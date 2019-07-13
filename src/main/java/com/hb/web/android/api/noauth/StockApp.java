package com.hb.web.android.api.noauth;

import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IOrderService;
import com.hb.web.api.IStockService;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.StockIndexModel;
import com.hb.web.vo.StockModel;
import com.hb.web.vo.appvo.request.StockQueryRequestVO;
import com.hb.web.vo.appvo.response.StockIndexQueryResponseVO;
import com.hb.web.vo.appvo.response.StockQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * ========== 股票相关 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.controller.StockController.java, v1.0
 * @date 2019年05月31日 11时02分
 */
@Api(tags = "[APP]股票")
@RestController
@RequestMapping("app/noauth/stock")
public class StockApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StockApp.class);

    @Autowired
    private IStockService iStockService;

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation(value = "根据股票代码获取股票信息")
    @PostMapping("/queryStockList")
    public AppResultModel<StockQueryResponseVO> queryStockList(@RequestBody StockQueryRequestVO stockQueryRequestVO) {
        List<StockModel> stockModels = iStockService.queryStockList(stockQueryRequestVO.getStockCodeSet());
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, new StockQueryResponseVO(stockModels));
    }

    @ApiOperation(value = "根据指数代码获取指数信息")
    @PostMapping("/queryStockIndexList")
    public AppResultModel<StockIndexQueryResponseVO> queryStockIndexList() {
        Set<String> stockCodeSet = new HashSet<>();
        // 上证指数
        stockCodeSet.add("000001");
        // 深圳成指
        stockCodeSet.add("399001");
        // 创业板指
        stockCodeSet.add("399006");
        List<StockIndexModel> stockIndexModels = iStockService.queryStockIndexList(stockCodeSet);
        Collections.sort(stockIndexModels, Comparator.comparing(StockIndexModel::getIndexCode));
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, new StockIndexQueryResponseVO(stockIndexModels));
    }

    @ApiOperation(value = "查询热门股票")
    @PostMapping("/getHotStockList")
    public AppResultModel<StockQueryResponseVO> getHotStockList() {
        LOGGER.info(LogUtils.appLog("查询热门股票"));
        // 获取订单中最热门的前几个股票代码
        int number = 9;
        Set<String> stockSet = iOrderService.getHotStockSet(number);
        LOGGER.info(LogUtils.appLog("查询热门股票，股票代码：{}"), stockSet);
        // 根据股票代码查询信息
        List<StockModel> stockModelList = iStockService.queryStockList(stockSet);
        LOGGER.info(LogUtils.appLog("查询热门股票，返回结果：{}"), stockModelList);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, new StockQueryResponseVO(stockModelList));
    }

}
