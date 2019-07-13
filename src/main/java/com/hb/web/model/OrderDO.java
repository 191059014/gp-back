package com.hb.web.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.web.annotation.SelfTableClass;
import com.hb.web.annotation.SelfTableColumn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * ========== 订单表 ==========
 *
 * @author Mr.huang
 * @version OrderDO.java, v1.0
 * @date 2019年06月20日 22时09分
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "订单模型")
@SelfTableClass(value = "t_order", comment = "订单表")
public class OrderDO extends BaseDO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 231294696872721081L;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    @SelfTableColumn(value = "orderId", id = true, comment = "订单ID")
    private String orderId;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @SelfTableColumn(value = "userId", comment = "用户ID")
    private String userId;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    @SelfTableColumn(value = "userName", comment = "用户姓名")
    private String userName;
    /**
     * 股票代码
     */
    @ApiModelProperty(value = "股票代码")
    @SelfTableColumn(value = "stockCode", comment = "股票代码")
    private String stockCode;
    /**
     * 股票名称
     */
    @ApiModelProperty(value = "股票名称")
    @SelfTableColumn(value = "stockName", comment = "股票名称")
    private String stockName;
    /**
     * 买入股数
     */
    @ApiModelProperty(value = "买入股数")
    @SelfTableColumn(value = "buyNumber", length = 12, comment = "买入股数")
    private Integer buyNumber;
    /**
     * 买入价格
     */
    @ApiModelProperty(value = "买入价格")
    @SelfTableColumn(value = "buyPrice", length = 12, defaultValue = "0", comment = "买入价格")
    private BigDecimal buyPrice;
    /**
     * 买入总金额
     */
    @ApiModelProperty(value = "买入总金额")
    @SelfTableColumn(value = "buyPriceTotal", length = 12, defaultValue = "0", comment = "买入总金额")
    private BigDecimal buyPriceTotal;
    /**
     * 卖出价格
     */
    @ApiModelProperty(value = "卖出价格")
    @SelfTableColumn(value = "sellPrice", length = 12, defaultValue = "0", comment = "卖出价格")
    private BigDecimal sellPrice;
    /**
     * 卖出总价格
     */
    @ApiModelProperty(value = "卖出总价格")
    @SelfTableColumn(value = "sellPriceTotal", length = 12, defaultValue = "0", comment = "卖出总价格")
    private BigDecimal sellPriceTotal;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    @SelfTableColumn(value = "orderStatus", length = 1, comment = "订单状态")
    private Integer orderStatus;
    /**
     * 利润
     */
    @ApiModelProperty(value = "利润")
    @SelfTableColumn(value = "profit", length = 12, defaultValue = "0", comment = "利润")
    private BigDecimal profit;
    /**
     * 盈亏率
     */
    @ApiModelProperty(value = "盈亏率")
    @SelfTableColumn(value = "profitRate", length = 12, defaultValue = "0", comment = "盈亏率")
    private BigDecimal profitRate;
    /**
     * 策略本金
     */
    @ApiModelProperty(value = "策略本金")
    @SelfTableColumn(value = "strategyOwnMoney", length = 12, defaultValue = "0", comment = "策略本金")
    private BigDecimal strategyOwnMoney;
    /**
     * 策略金额
     */
    @ApiModelProperty(value = "策略金额")
    @SelfTableColumn(value = "strategyMoney", length = 12, defaultValue = "0", comment = "策略金额")
    private BigDecimal strategyMoney;

    /**
     * 止盈价格
     */
    @ApiModelProperty(value = "止盈价格")
    @SelfTableColumn(value = "stopEarnMoney", length = 12, defaultValue = "0", comment = "止盈价格")
    private BigDecimal stopEarnMoney;

    /**
     * 止损价格
     */
    @ApiModelProperty(value = "止损价格")
    @SelfTableColumn(value = "stopLossMoney", length = 12, defaultValue = "0", comment = "止损价格")
    private BigDecimal stopLossMoney;

    /**
     * 信息服务费
     */
    @ApiModelProperty(value = "信息服务费")
    @SelfTableColumn(value = "serviceMoney", length = 12, defaultValue = "0", comment = "信息服务费")
    private BigDecimal serviceMoney;

    /**
     * 递延金
     */
    @ApiModelProperty(value = "递延金")
    @SelfTableColumn(value = "delayMoney", length = 12, defaultValue = "0", comment = "递延金")
    private BigDecimal delayMoney;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getSellPriceTotal() {
        return sellPriceTotal;
    }

    public void setSellPriceTotal(BigDecimal sellPriceTotal) {
        this.sellPriceTotal = sellPriceTotal;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
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
        return "OrderDO{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", buyNumber=" + buyNumber +
                ", buyPrice=" + buyPrice +
                ", buyPriceTotal=" + buyPriceTotal +
                ", sellPrice=" + sellPrice +
                ", sellPriceTotal=" + sellPriceTotal +
                ", orderStatus='" + orderStatus + '\'' +
                ", profit=" + profit +
                ", profitRate=" + profitRate +
                ", strategyOwnMoney=" + strategyOwnMoney +
                ", strategyMoney=" + strategyMoney +
                ", stopEarnMoney=" + stopEarnMoney +
                ", stopLossMoney=" + stopLossMoney +
                ", serviceMoney=" + serviceMoney +
                ", delayMoney=" + delayMoney +
                '}';
    }
}
