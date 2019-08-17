package com.hb.facade.vo.appvo.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ========== 订单请求实体 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.OrderRequestVO.java, v1.0
 * @date 2019年06月26日 10时34分
 */
public class OrderRequestVO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3214202179100159395L;
    /**
     * 股票代码
     */
    @ApiModelProperty(value = "股票代码")
    private String stockCode;
    /**
     * 股票名称
     */
    @ApiModelProperty(value = "股票名称")
    private String stockName;
    /**
     * 买入股数
     */
    @ApiModelProperty(value = "买入股数")
    private Integer buyNumber;
    /**
     * 买入价格
     */
    @ApiModelProperty(value = "买入价格")
    private BigDecimal buyPrice;
    /**
     * 买入总金额
     */
    @ApiModelProperty(value = "买入总金额")
    private BigDecimal buyPriceTotal;

    /**
     * 策略本金
     */
    @ApiModelProperty(value = "策略本金")
    private BigDecimal strategyOwnMoney;
    /**
     * 策略金额
     */
    @ApiModelProperty(value = "策略金额")
    private BigDecimal strategyMoney;

    /**
     * 止盈价格
     */
    @ApiModelProperty(value = "止盈价格")
    private BigDecimal stopEarnMoney;

    /**
     * 止损价格
     */
    @ApiModelProperty(value = "止损价格")
    private BigDecimal stopLossMoney;

    /**
     * 信息服务费
     */
    @ApiModelProperty(value = "信息服务费")
    private BigDecimal serviceMoney;

    /**
     * 递延金
     */
    @ApiModelProperty(value = "递延金")
    private BigDecimal delayMoney;

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

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getBuyPriceTotal() {
        return buyPriceTotal;
    }

    public void setBuyPriceTotal(BigDecimal buyPriceTotal) {
        this.buyPriceTotal = buyPriceTotal;
    }

    public BigDecimal getStrategyOwnMoney() {
        return strategyOwnMoney;
    }

    public void setStrategyOwnMoney(BigDecimal strategyOwnMoney) {
        this.strategyOwnMoney = strategyOwnMoney;
    }

    public BigDecimal getStrategyMoney() {
        return strategyMoney;
    }

    public void setStrategyMoney(BigDecimal strategyMoney) {
        this.strategyMoney = strategyMoney;
    }

    public BigDecimal getStopEarnMoney() {
        return stopEarnMoney;
    }

    public void setStopEarnMoney(BigDecimal stopEarnMoney) {
        this.stopEarnMoney = stopEarnMoney;
    }

    public BigDecimal getStopLossMoney() {
        return stopLossMoney;
    }

    public void setStopLossMoney(BigDecimal stopLossMoney) {
        this.stopLossMoney = stopLossMoney;
    }

    public BigDecimal getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public BigDecimal getDelayMoney() {
        return delayMoney;
    }

    public void setDelayMoney(BigDecimal delayMoney) {
        this.delayMoney = delayMoney;
    }

    @Override
    public String toString() {
        return "OrderRequestVO{" +
                "stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", buyNumber=" + buyNumber +
                ", buyPrice=" + buyPrice +
                ", buyPriceTotal=" + buyPriceTotal +
                ", strategyOwnMoney=" + strategyOwnMoney +
                ", strategyMoney=" + strategyMoney +
                ", stopEarnMoney=" + stopEarnMoney +
                ", stopLossMoney=" + stopLossMoney +
                ", serviceMoney=" + serviceMoney +
                ", delayMoney=" + delayMoney +
                '}';
    }
}
