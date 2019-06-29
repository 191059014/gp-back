package com.hb.web.vo;

import com.hb.web.constant.GeneralConst;
import com.hb.web.util.BigDecimalUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ========== 股票信息 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.vo.StockModel.java, v1.0
 * @date 2019年05月31日 10时51分
 */
@ApiModel(description = "查询第三方股票信息对应的数据模型")
public class StockModel implements Serializable {

    private static final long serialVersionUID = -5584039422657554960L;

    // 股票代码
    @ApiModelProperty(value = "股票代码")
    private String stockCode;
    // 股票名称
    @ApiModelProperty(value = "股票名称")
    private String stockName;
    // 今日开盘价
    @ApiModelProperty(value = "今日开盘价")
    private BigDecimal todayOpeningPrice;
    // 昨日收盘价
    @ApiModelProperty(value = "昨日收盘价")
    private BigDecimal yesterdayClosePrice;
    // 当前价格
    @ApiModelProperty(value = "当前价格")
    private BigDecimal currentPrice;
    // 今日最高价
    @ApiModelProperty(value = "今日最高价")
    private BigDecimal todayMaxPrice;
    // 今日最低价
    @ApiModelProperty(value = "今日最低价")
    private BigDecimal todayMinPrice;
    // 竞买价，即买一报价
    @ApiModelProperty(value = "竞买价，即买一报价")
    private BigDecimal biddingPrice;
    // 竞卖价，即卖一报价
    @ApiModelProperty(value = "竞卖价，即卖一报价")
    private BigDecimal auctionPrice;
    // 成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百
    @ApiModelProperty(value = "成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百")
    private BigDecimal volNumber;
    // 成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万
    @ApiModelProperty(value = "成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万")
    private BigDecimal volPrice;
    // 买一申请的股数
    @ApiModelProperty(value = "买一申请的股数")
    private BigDecimal buyFirstApplyNumber;
    // 买一报价
    @ApiModelProperty(value = "买一报价")
    private BigDecimal buyFirstOffer;
    // 买二申请的股数
    @ApiModelProperty(value = "买二申请的股数")
    private BigDecimal buySecondApplyNumber;
    // 买二报价
    @ApiModelProperty(value = "买二报价")
    private BigDecimal buySecondOffer;
    // 买三申请的股数
    @ApiModelProperty(value = "买三申请的股数")
    private BigDecimal buyThirdApplyNumber;
    // 买三报价
    @ApiModelProperty(value = "买三报价")
    private BigDecimal buyThirdOffer;
    // 买四申请的股数
    @ApiModelProperty(value = "买四申请的股数")
    private BigDecimal buyFourthApplyNumber;
    // 买四报价
    @ApiModelProperty(value = "买四报价")
    private BigDecimal buyFourthOffer;
    // 买五申请的股数
    @ApiModelProperty(value = "买五申请的股数")
    private BigDecimal buyFifthApplyNumber;
    // 买五报价
    @ApiModelProperty(value = "买五报价")
    private BigDecimal buyFifthOffer;
    // 卖一申报的股数
    @ApiModelProperty(value = "卖一申报的股数")
    private BigDecimal sellFirstApplyNumber;
    // 卖一报价
    @ApiModelProperty(value = "卖一报价")
    private BigDecimal sellFirstOffer;
    // 卖二申报的股数
    @ApiModelProperty(value = "卖二申报的股数")
    private BigDecimal sellSecondApplyNumber;
    // 卖二报价
    @ApiModelProperty(value = "卖二报价")
    private BigDecimal sellSecondOffer;
    // 卖三申报的股数
    @ApiModelProperty(value = "卖三申报的股数")
    private BigDecimal sellThirdApplyNumber;
    // 卖三报价
    @ApiModelProperty(value = "卖三报价")
    private BigDecimal sellThirdOffer;
    // 卖四申报的股数
    @ApiModelProperty(value = "卖四申报的股数")
    private BigDecimal sellFourthApplyNumber;
    // 卖四报价
    @ApiModelProperty(value = "卖四报价")
    private BigDecimal sellFourthOffer;
    // 卖五申报的股数
    @ApiModelProperty(value = "卖五申报的股数")
    private BigDecimal sellFifthApplyNumber;
    // 卖五报价
    @ApiModelProperty(value = "卖五报价")
    private BigDecimal sellFifthOffer;
    // 日期
    @ApiModelProperty(value = "日期")
    private String date;
    // 时间
    @ApiModelProperty(value = "时间")
    private String time;

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getTodayOpeningPrice() {
        return todayOpeningPrice;
    }

    public void setTodayOpeningPrice(BigDecimal todayOpeningPrice) {
        this.todayOpeningPrice = todayOpeningPrice;
    }

    public BigDecimal getYesterdayClosePrice() {
        return yesterdayClosePrice;
    }

    public void setYesterdayClosePrice(BigDecimal yesterdayClosePrice) {
        this.yesterdayClosePrice = yesterdayClosePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getTodayMaxPrice() {
        return todayMaxPrice;
    }

    public void setTodayMaxPrice(BigDecimal todayMaxPrice) {
        this.todayMaxPrice = todayMaxPrice;
    }

    public BigDecimal getTodayMinPrice() {
        return todayMinPrice;
    }

    public void setTodayMinPrice(BigDecimal todayMinPrice) {
        this.todayMinPrice = todayMinPrice;
    }

    public BigDecimal getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(BigDecimal biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public BigDecimal getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(BigDecimal auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public BigDecimal getVolNumber() {
        return volNumber;
    }

    public void setVolNumber(BigDecimal volNumber) {
        this.volNumber = volNumber;
    }

    public BigDecimal getVolPrice() {
        return volPrice;
    }

    public void setVolPrice(BigDecimal volPrice) {
        this.volPrice = volPrice;
    }

    public BigDecimal getBuyFirstApplyNumber() {
        return buyFirstApplyNumber;
    }

    public void setBuyFirstApplyNumber(BigDecimal buyFirstApplyNumber) {
        this.buyFirstApplyNumber = buyFirstApplyNumber;
    }

    public BigDecimal getBuyFirstOffer() {
        return buyFirstOffer;
    }

    public void setBuyFirstOffer(BigDecimal buyFirstOffer) {
        this.buyFirstOffer = buyFirstOffer;
    }

    public BigDecimal getBuySecondApplyNumber() {
        return buySecondApplyNumber;
    }

    public void setBuySecondApplyNumber(BigDecimal buySecondApplyNumber) {
        this.buySecondApplyNumber = buySecondApplyNumber;
    }

    public BigDecimal getBuySecondOffer() {
        return buySecondOffer;
    }

    public void setBuySecondOffer(BigDecimal buySecondOffer) {
        this.buySecondOffer = buySecondOffer;
    }

    public BigDecimal getBuyThirdApplyNumber() {
        return buyThirdApplyNumber;
    }

    public void setBuyThirdApplyNumber(BigDecimal buyThirdApplyNumber) {
        this.buyThirdApplyNumber = buyThirdApplyNumber;
    }

    public BigDecimal getBuyThirdOffer() {
        return buyThirdOffer;
    }

    public void setBuyThirdOffer(BigDecimal buyThirdOffer) {
        this.buyThirdOffer = buyThirdOffer;
    }

    public BigDecimal getBuyFourthApplyNumber() {
        return buyFourthApplyNumber;
    }

    public void setBuyFourthApplyNumber(BigDecimal buyFourthApplyNumber) {
        this.buyFourthApplyNumber = buyFourthApplyNumber;
    }

    public BigDecimal getBuyFourthOffer() {
        return buyFourthOffer;
    }

    public void setBuyFourthOffer(BigDecimal buyFourthOffer) {
        this.buyFourthOffer = buyFourthOffer;
    }

    public BigDecimal getBuyFifthApplyNumber() {
        return buyFifthApplyNumber;
    }

    public void setBuyFifthApplyNumber(BigDecimal buyFifthApplyNumber) {
        this.buyFifthApplyNumber = buyFifthApplyNumber;
    }

    public BigDecimal getBuyFifthOffer() {
        return buyFifthOffer;
    }

    public void setBuyFifthOffer(BigDecimal buyFifthOffer) {
        this.buyFifthOffer = buyFifthOffer;
    }

    public BigDecimal getSellFirstApplyNumber() {
        return sellFirstApplyNumber;
    }

    public void setSellFirstApplyNumber(BigDecimal sellFirstApplyNumber) {
        this.sellFirstApplyNumber = sellFirstApplyNumber;
    }

    public BigDecimal getSellFirstOffer() {
        return sellFirstOffer;
    }

    public void setSellFirstOffer(BigDecimal sellFirstOffer) {
        this.sellFirstOffer = sellFirstOffer;
    }

    public BigDecimal getSellSecondApplyNumber() {
        return sellSecondApplyNumber;
    }

    public void setSellSecondApplyNumber(BigDecimal sellSecondApplyNumber) {
        this.sellSecondApplyNumber = sellSecondApplyNumber;
    }

    public BigDecimal getSellSecondOffer() {
        return sellSecondOffer;
    }

    public void setSellSecondOffer(BigDecimal sellSecondOffer) {
        this.sellSecondOffer = sellSecondOffer;
    }

    public BigDecimal getSellThirdApplyNumber() {
        return sellThirdApplyNumber;
    }

    public void setSellThirdApplyNumber(BigDecimal sellThirdApplyNumber) {
        this.sellThirdApplyNumber = sellThirdApplyNumber;
    }

    public BigDecimal getSellThirdOffer() {
        return sellThirdOffer;
    }

    public void setSellThirdOffer(BigDecimal sellThirdOffer) {
        this.sellThirdOffer = sellThirdOffer;
    }

    public BigDecimal getSellFourthApplyNumber() {
        return sellFourthApplyNumber;
    }

    public void setSellFourthApplyNumber(BigDecimal sellFourthApplyNumber) {
        this.sellFourthApplyNumber = sellFourthApplyNumber;
    }

    public BigDecimal getSellFourthOffer() {
        return sellFourthOffer;
    }

    public void setSellFourthOffer(BigDecimal sellFourthOffer) {
        this.sellFourthOffer = sellFourthOffer;
    }

    public BigDecimal getSellFifthApplyNumber() {
        return sellFifthApplyNumber;
    }

    public void setSellFifthApplyNumber(BigDecimal sellFifthApplyNumber) {
        this.sellFifthApplyNumber = sellFifthApplyNumber;
    }

    public BigDecimal getSellFifthOffer() {
        return sellFifthOffer;
    }

    public void setSellFifthOffer(BigDecimal sellFifthOffer) {
        this.sellFifthOffer = sellFifthOffer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "stockName='" + stockName + '\'' +
                ", todayOpeningPrice=" + todayOpeningPrice +
                ", yesterdayClosePrice=" + yesterdayClosePrice +
                ", currentPrice=" + currentPrice +
                ", todayMaxPrice=" + todayMaxPrice +
                ", todayMinPrice=" + todayMinPrice +
                ", biddingPrice=" + biddingPrice +
                ", auctionPrice=" + auctionPrice +
                ", volNumber=" + volNumber +
                ", volPrice=" + volPrice +
                ", buyFirstApplyNumber=" + buyFirstApplyNumber +
                ", buyFirstOffer=" + buyFirstOffer +
                ", buySecondApplyNumber=" + buySecondApplyNumber +
                ", buySecondOffer=" + buySecondOffer +
                ", buyThirdApplyNumber=" + buyThirdApplyNumber +
                ", buyThirdOffer=" + buyThirdOffer +
                ", buyFourthApplyNumber=" + buyFourthApplyNumber +
                ", buyFourthOffer=" + buyFourthOffer +
                ", buyFifthApplyNumber=" + buyFifthApplyNumber +
                ", buyFifthOffer=" + buyFifthOffer +
                ", sellFirstApplyNumber=" + sellFirstApplyNumber +
                ", sellFirstOffer=" + sellFirstOffer +
                ", sellSecondApplyNumber=" + sellSecondApplyNumber +
                ", sellSecondOffer=" + sellSecondOffer +
                ", sellThirdApplyNumber=" + sellThirdApplyNumber +
                ", sellThirdOffer=" + sellThirdOffer +
                ", sellFourthApplyNumber=" + sellFourthApplyNumber +
                ", sellFourthOffer=" + sellFourthOffer +
                ", sellFifthApplyNumber=" + sellFifthApplyNumber +
                ", sellFifthOffer=" + sellFifthOffer +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    /**
     * ########## 后续处理 ##########
     */
    public void postHandler() {
        // 以”百股“为单位
        this.setVolNumber(BigDecimalUtils.divide(this.getVolNumber(), GeneralConst.ONE_HUNDRED_D));
        // 以”万元“为单位
        this.setVolPrice(BigDecimalUtils.divide(this.getVolPrice(), GeneralConst.TEN_THOUSAND_D));
        this.setBuyFirstApplyNumber(BigDecimalUtils.divide(this.getBuyFirstApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setBuySecondApplyNumber(BigDecimalUtils.divide(this.getBuySecondApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setBuyThirdApplyNumber(BigDecimalUtils.divide(this.getBuyThirdApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setBuyFourthApplyNumber(BigDecimalUtils.divide(this.getBuyFourthApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setBuyFifthApplyNumber(BigDecimalUtils.divide(this.getBuyFifthApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setSellFirstApplyNumber(BigDecimalUtils.divide(this.getSellFirstApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setSellSecondApplyNumber(BigDecimalUtils.divide(this.getSellSecondApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setSellThirdApplyNumber(BigDecimalUtils.divide(this.getSellThirdApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setSellFourthApplyNumber(BigDecimalUtils.divide(this.getSellFourthApplyNumber(), GeneralConst.ONE_HUNDRED_D));
        this.setSellFifthApplyNumber(BigDecimalUtils.divide(this.getSellFifthApplyNumber(), GeneralConst.ONE_HUNDRED_D));
    }

}
