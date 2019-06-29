package com.hb.web.vo;

import com.hb.web.constant.GeneralConst;
import com.hb.web.util.BigDecimalUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ========== 股票指数信息Model ==========
 *
 * @author Mr.huang
 * @version StockIndexModel.java, v1.0
 * @date 2019年06月18日 17时05分
 */
@ApiModel(description = "股票指数信息")
public class StockIndexModel implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5210166925250693942L;
    // 指数代码
    @ApiModelProperty(value = "指数代码")
    private String indexCode;
    // 指数名称
    @ApiModelProperty(value = "指数名称")
    private String indexName;
    // 当前指数
    @ApiModelProperty(value = "当前指数")
    private BigDecimal currentIndex;
    // 今日变化值
    @ApiModelProperty(value = "今日变化值")
    private BigDecimal todayChangeValue;
    // 今日变化百分比
    @ApiModelProperty(value = "今日变化百分比")
    private BigDecimal todayChangePercent;
    // 成交量（手）
    @ApiModelProperty(value = "成交量（手）")
    private BigDecimal tradeQuantity;
    // 成交额（万元）
    @ApiModelProperty(value = "成交额（万元）")
    private BigDecimal tradeMoney;

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public BigDecimal getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(BigDecimal currentIndex) {
        this.currentIndex = currentIndex;
    }

    public BigDecimal getTodayChangeValue() {
        return todayChangeValue;
    }

    public void setTodayChangeValue(BigDecimal todayChangeValue) {
        this.todayChangeValue = todayChangeValue;
    }

    public BigDecimal getTodayChangePercent() {
        return todayChangePercent;
    }

    public void setTodayChangePercent(BigDecimal todayChangePercent) {
        this.todayChangePercent = todayChangePercent;
    }

    public BigDecimal getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(BigDecimal tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    @Override
    public String toString() {
        return "StockIndexModel{" +
                "indexCode='" + indexCode + '\'' +
                "indexName='" + indexName + '\'' +
                ", currentIndex=" + currentIndex +
                ", todayChangeValue=" + todayChangeValue +
                ", todayChangePercent=" + todayChangePercent +
                ", tradeQuantity=" + tradeQuantity +
                ", tradeMoney=" + tradeMoney +
                '}';
    }

    /**
     * ########## 后续处理 ##########
     */
    public void postHandler() {
        this.setCurrentIndex(this.getCurrentIndex().setScale(2, BigDecimal.ROUND_HALF_UP));
        this.setTodayChangePercent(this.getTodayChangePercent().setScale(2, BigDecimal.ROUND_HALF_UP));
        this.setTodayChangeValue(this.getTodayChangeValue().setScale(2, BigDecimal.ROUND_HALF_UP));
        // 以”百股“为单位
        this.setTradeQuantity(BigDecimalUtils.divide(this.getTradeQuantity(), GeneralConst.ONE_HUNDRED_D, 2));
        // 以”万元“为单位
        this.setTradeMoney(BigDecimalUtils.divide(this.getTradeMoney(), GeneralConst.TEN_THOUSAND_D, 2));
    }

}
