package com.hb.web.android.api.noauth;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.IOrderService;
import com.hb.web.api.IStockService;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.model.StockListDO;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.StockIndexModel;
import com.hb.web.vo.StockModel;
import com.hb.web.vo.appvo.request.StockQueryPageRequestVO;
import com.hb.web.vo.appvo.request.StockQueryRequestVO;
import com.hb.web.vo.appvo.response.StockIndexQueryResponseVO;
import com.hb.web.vo.appvo.response.StockQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        LOGGER.info(LogUtils.appLog("根据股票代码获取股票信息，入参：{}"), stockQueryRequestVO);
        List<StockModel> stockModels = null;
        try {
            stockModels = iStockService.queryStockList(stockQueryRequestVO.getStockCodeSet());
        } catch (Exception e) {
            String stackTrace = LogUtils.getStackTrace(e);
            LOGGER.error(LogUtils.appLog("根据股票代码获取股票信息,系统异常：{}"), stackTrace);
            alarmTools.alert("APP", "股票", "根据股票代码获取股票信息", e.getMessage());
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        LOGGER.info(LogUtils.appLog("根据股票代码获取股票信息，出参：{}"), stockModels);
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
        List<StockIndexModel> stockIndexModels = null;
        try {
            stockIndexModels = iStockService.queryStockIndexList(stockCodeSet);
        } catch (Exception e) {
            String stackTrace = LogUtils.getStackTrace(e);
            LOGGER.error(LogUtils.appLog("根据指数代码获取指数信息,系统异常：{}"), stackTrace);
            alarmTools.alert("APP", "股票", "根据指数代码获取指数信息", e.getMessage());
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        if (CollectionUtils.isNotEmpty(stockIndexModels)) {
            Collections.sort(stockIndexModels, Comparator.comparing(StockIndexModel::getIndexCode));
        }
        LOGGER.info(LogUtils.appLog("根据指数代码获取指数信息，出参：{}"), stockIndexModels);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, new StockIndexQueryResponseVO(stockIndexModels));
    }

    @ApiOperation(value = "查询热门股票")
    @PostMapping("/getHotStockList")
    public AppResultModel<StockQueryResponseVO> getHotStockList() {
        // 获取订单中最热门的前几个股票代码
        int number = 9;
        List<StockModel> stockModelList = null;
        try {
            Set<String> stockSet = iOrderService.getHotStockSet(number);
            LOGGER.info(LogUtils.appLog("查询热门股票，股票代码：{}"), stockSet);
            // 根据股票代码查询信息
            stockModelList = iStockService.queryStockList(stockSet);
        } catch (Exception e) {
            String stackTrace = LogUtils.getStackTrace(e);
            LOGGER.error(LogUtils.appLog("查询热门股票,系统异常：{}"), stackTrace);
            alarmTools.alert("APP", "股票", "查询热门股票", e.getMessage());
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        LOGGER.info(LogUtils.appLog("查询热门股票，出参：{}"), stockModelList);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, new StockQueryResponseVO(stockModelList));
    }

    @ApiOperation(value = "根据股票代码模糊搜索股票信息")
    @PostMapping("/findPageList")
    public AppResultModel<List<StockListDO>> findPageList(@RequestBody StockQueryPageRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("根据股票代码模糊搜索股票信息，入参：{}"), requestVO);
        String stockCode = requestVO.getStockCode();
        if (StringUtils.isBlank(stockCode)) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
        }
        Integer pageSize = requestVO.getPageSize();
        if (pageSize == null || pageSize == 0) {
            pageSize = 100;
        }
        Integer startRow = requestVO.getStartRow() == null ? 0 : requestVO.getStartRow();
        List<StockListDO> pageList = iStockService.findPageList(stockCode, startRow, pageSize);
        LOGGER.info(LogUtils.appLog("根据股票代码模糊搜索股票信息，出参：{}"), pageList);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, pageList);
    }

}
