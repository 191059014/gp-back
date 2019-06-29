package com.hb.web.impl;

import com.hb.web.api.IStockService;
import com.hb.web.constant.GeneralConst;
import com.hb.web.constant.enumutil.ExchangeTypeEnum;
import com.hb.web.vo.StockIndexModel;
import com.hb.web.vo.StockModel;
import com.hb.web.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * ========== 股票相关service实现类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.impl.StockServiceImpl.java, v1.0
 * @date 2019年05月31日 11时06分
 */
@RestController
@RequestMapping("/service/stock")
public class StockServiceImpl implements IStockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

    @RequestMapping("/queryStockInfoByStockCode")
    @Override
    public List<StockModel> queryStockList(Set<String> stockCodeSet) {
        LOGGER.info("查询股票信息入参：{}", stockCodeSet);
        // 拼装请求参数
        String queryParamValue = builderParameter(stockCodeSet, 1);
        if (StringUtils.isBlank(queryParamValue)) {
            return null;
        }
        // 调用新浪接口查询
        String response = invoke(queryParamValue);
        if (StringUtils.isBlank(response)) {
            return null;
        }
        // 组装结果
        String[] singleArr = response.split(";");
        List<StockModel> resultList = new ArrayList<>();
        for (String single : singleArr) {
            String[] singleResultArr = single.split("=");
            if (singleResultArr.length < 2) {
                continue;
            }
            String left = singleResultArr[0];
            if (left.length() < 6) {
                continue;
            }
            String stockCode = left.substring(left.length() - 6);
            String[] msgArr = singleResultArr[1]
                    .replaceAll("\"", "")
                    .replaceAll(";", "")
                    .split(",");
            // 设置属性的值
            StockModel stockModel = makeStockModelInfo(msgArr);
            stockModel.setStockCode(stockCode);
            resultList.add(stockModel);
        }
        LOGGER.info("查询股票信息返回结果：{}", resultList);
        return resultList;
    }

    @Override
    public List<StockIndexModel> queryStockIndexList(Set<String> indexCodeSet) {
        LOGGER.info("查询指数信息入参：{}", indexCodeSet);
        // 拼装请求参数
        String queryParamValue = builderParameter(indexCodeSet, 2);
        if (StringUtils.isBlank(queryParamValue)) {
            return null;
        }
        // 调用新浪接口查询
        String response = invoke(queryParamValue);
        if (StringUtils.isBlank(response)) {
            return null;
        }
        // 组装结果
        String[] singleArr = response.split(";");
        List<StockIndexModel> resultList = new ArrayList<>();
        for (String single : singleArr) {
            String[] singleResultArr = single.split("=");
            if (singleResultArr.length < 2) {
                continue;
            }
            String left = singleResultArr[0];
            if (left.length() < 6) {
                continue;
            }
            String stockCode = left.substring(left.length() - 6);
            String[] msgArr = singleResultArr[1]
                    .replaceAll("\"", "")
                    .replaceAll(";", "")
                    .split(",");

            // 设置属性的值
            StockIndexModel stockIndexModel = makeStockIndexModelInfo(msgArr);
            stockIndexModel.setIndexCode(stockCode);
            resultList.add(stockIndexModel);
        }
        LOGGER.info("查询股票信息返回结果：{}", resultList);
        return resultList;
    }

    /**
     * ########## 将数组里的值填充到对象里面 ##########
     *
     * @param arr 数组
     * @return StockModel
     */
    private StockModel makeStockModelInfo(String[] arr) {
        StockModel model = new StockModel();
        model.setStockName(convertString(arr, 0));
        model.setTodayOpeningPrice(convertBigDecimal(arr, 1));
        model.setYesterdayClosePrice(convertBigDecimal(arr, 2));
        model.setCurrentPrice(convertBigDecimal(arr, 3));
        model.setTodayMaxPrice(convertBigDecimal(arr, 4));
        model.setTodayMinPrice(convertBigDecimal(arr, 5));
        model.setBiddingPrice(convertBigDecimal(arr, 6));
        model.setAuctionPrice(convertBigDecimal(arr, 7));
        model.setVolNumber(convertBigDecimal(arr, 8));
        model.setVolPrice(convertBigDecimal(arr, 9));
        model.setBuyFirstApplyNumber(convertBigDecimal(arr, 10));
        model.setBuyFirstOffer(convertBigDecimal(arr, 11));
        model.setBuySecondApplyNumber(convertBigDecimal(arr, 12));
        model.setBuySecondOffer(convertBigDecimal(arr, 13));
        model.setBuyThirdApplyNumber(convertBigDecimal(arr, 14));
        model.setBuyThirdOffer(convertBigDecimal(arr, 15));
        model.setBuyFourthApplyNumber(convertBigDecimal(arr, 16));
        model.setBuyFourthOffer(convertBigDecimal(arr, 17));
        model.setBuyFifthApplyNumber(convertBigDecimal(arr, 18));
        model.setBuyFifthOffer(convertBigDecimal(arr, 19));
        model.setSellFirstApplyNumber(convertBigDecimal(arr, 20));
        model.setSellFirstOffer(convertBigDecimal(arr, 21));
        model.setSellSecondApplyNumber(convertBigDecimal(arr, 22));
        model.setSellSecondOffer(convertBigDecimal(arr, 23));
        model.setSellThirdApplyNumber(convertBigDecimal(arr, 24));
        model.setSellThirdOffer(convertBigDecimal(arr, 25));
        model.setSellFourthApplyNumber(convertBigDecimal(arr, 26));
        model.setSellFourthOffer(convertBigDecimal(arr, 27));
        model.setSellFifthApplyNumber(convertBigDecimal(arr, 28));
        model.setSellFifthOffer(convertBigDecimal(arr, 29));
        model.setDate(convertString(arr, 30));
        model.setTime(convertString(arr, 31));
        // 后续处理
        model.postHandler();
        return model;
    }

    /**
     * ########## 转换字符串的值 ##########
     *
     * @param arr   数组
     * @param index 下标
     * @return 字符串
     */
    private String convertString(String[] arr, int index) {
        return arr.length > index ? arr[index] : null;
    }

    /**
     * ########## 转换BigDecimal的值 ##########
     *
     * @param arr   数组
     * @param index 下标
     * @return BigDecimal
     */
    private BigDecimal convertBigDecimal(String[] arr, int index) {
        return arr.length > index ? new BigDecimal(arr[index]) : BigDecimal.ZERO;
    }

    /**
     * ########## 调用新浪股票实时查询接口 ##########
     *
     * @param queryParamValue 请求的参数值字符串
     * @return 相应结果
     */
    private String invoke(String queryParamValue) {
        Map<String, String> parameter = new HashMap<>();
        parameter.put(GeneralConst.SINA_URL_PARAM, queryParamValue);
        String response = HttpUtils.get(GeneralConst.SINA_URL, parameter, HttpUtils.ENCODE_DEFAULT);
        LOGGER.info("调用新浪股票实时查询接口返回数据：{}", response);
        return response;
    }

    /**
     * ########## 组装请求参数值 ##########
     *
     * @param codeSet 代码集合
     * @param flag    1-普通的股票，2-指数
     * @return 参数值集合
     */
    private String builderParameter(Set<String> codeSet, int flag) {
        LOGGER.info("组装请求参数值，入参：{}=={}", codeSet, flag);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String code : codeSet) {
            String queryString = flag == 1 ? ExchangeTypeEnum.commonRule(code)
                    : flag == 2 ? ExchangeTypeEnum.specialRule(code)
                    : "";
            sb.append(queryString);
            if (i != codeSet.size() - 1) {
                sb.append(",");
            }
            i++;
        }
        String queryParamValue = sb.toString();
        LOGGER.info("组装请求参数值，出参：{}", queryParamValue);
        return queryParamValue;
    }

    /**
     * ########## 将数组里的值填充到对象里面 ##########
     *
     * @param arr 数组
     * @return StockModel
     */
    private StockIndexModel makeStockIndexModelInfo(String[] arr) {
        StockIndexModel model = new StockIndexModel();
        model.setIndexName(convertString(arr, 0));
        model.setCurrentIndex(convertBigDecimal(arr, 1));
        model.setTodayChangeValue(convertBigDecimal(arr, 2));
        model.setTodayChangePercent(convertBigDecimal(arr, 3));
        model.setTradeQuantity(convertBigDecimal(arr, 4));
        model.setTradeMoney(convertBigDecimal(arr, 5));
        model.postHandler();
        return model;
    }


}
