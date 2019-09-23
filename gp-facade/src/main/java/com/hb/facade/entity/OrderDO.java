package com.hb.facade.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hb.unic.base.annotation.SelfTableClass;
import com.hb.unic.base.annotation.SelfTableColumn;
import com.hb.unic.util.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

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
     * 买入时间
     */
    @ApiModelProperty(value = "买入时间")
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    @SelfTableColumn(value = "buyTime", defaultValue = "CURRENT_TIMESTAMP", comment = "买入时间")
    private Date buyTime;
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
     * 递延单价
     */
    @ApiModelProperty(value = "递延单价")
    @SelfTableColumn(value = "delayMoneyUnitPrice", length = 12, defaultValue = "0", comment = "递延单价")
    private BigDecimal delayMoneyUnitPrice;
    /**
     * 递延总金额
     */
    @ApiModelProperty(value = "递延总金额")
    @SelfTableColumn(value = "delayMoney", length = 12, defaultValue = "0", comment = "递延金")
    private BigDecimal delayMoney;
    /**
     * 递延天数
     */
    @ApiModelProperty(value = "总共可递延天数")
    @SelfTableColumn(value = "delayDays", length = 12, comment = "总共可递延天数")
    private Integer delayDays;
    /**
     * 已递延天数
     */
    @ApiModelProperty(value = "已递延天数")
    @SelfTableColumn(value = "alreadyDelayDays", length = 12, comment = "已递延天数")
    private Integer alreadyDelayDays;
    /**
     * 剩余递延天数
     */
    @ApiModelProperty(value = "剩余递延天数")
    @SelfTableColumn(value = "residueDelayDays", length = 12, comment = "剩余递延天数")
    private Integer residueDelayDays;
    /**
     * 退还递延天数
     */
    @ApiModelProperty(value = "退还递延天数")
    @SelfTableColumn(value = "backDelayDays", length = 12, comment = "退换递延天数")
    private Integer backDelayDays;
    /**
     * 退还递延金额
     */
    @ApiModelProperty(value = "退还递延金额")
    @SelfTableColumn(value = "backDelayMoney", length = 12, comment = "退换递延金额")
    private BigDecimal backDelayMoney;
    /**
     * 递延到期时间
     */
    @ApiModelProperty(value = "递延到期时间")
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    @SelfTableColumn(value = "delayEndTime", defaultValue = "CURRENT_TIMESTAMP", comment = "递延到期时间")
    private Date delayEndTime;
    /**
     * 委托价格
     */
    @ApiModelProperty(value = "委托价格")
    @SelfTableColumn(value = "entrustPrice", length = 12, defaultValue = "0", comment = "委托价格")
    private BigDecimal entrustPrice;
    /**
     * 委托股数
     */
    @ApiModelProperty(value = "委托股数")
    @SelfTableColumn(value = "entrustNumber", length = 12, defaultValue = "0", comment = "委托股数")
    private Integer entrustNumber;
    /**
     * 卖出股数
     */
    @ApiModelProperty(value = "卖出股数")
    @SelfTableColumn(value = "sellNumber", length = 11, defaultValue = "0", comment = "卖出股数")
    private Integer sellNumber;
    /**
     * 卖出时间
     */
    @ApiModelProperty(value = "卖出时间")
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    @SelfTableColumn(value = "sellTime", defaultValue = "CURRENT_TIMESTAMP", comment = "卖出时间")
    private Date sellTime;
    /**
     * 取消时间
     */
    @ApiModelProperty(value = "取消时间")
    @JsonFormat(pattern = DateUtils.DEFAULT_FORMAT, timezone = DateUtils.DEFAULT_TIMEZONE)
    @SelfTableColumn(value = "cancelTime", defaultValue = "CURRENT_TIMESTAMP", comment = "取消时间")
    private Date cancelTime;

    public OrderDO() {
    }

    public OrderDO(String orderId) {
        this.orderId = orderId;
    }

    public OrderDO(String orderId, String userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

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

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getDelayEndTime() {
        return delayEndTime;
    }

    public void setDelayEndTime(Date delayEndTime) {
        this.delayEndTime = delayEndTime;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public Integer getEntrustNumber() {
        return entrustNumber;
    }

    public void setEntrustNumber(Integer entrustNumber) {
        this.entrustNumber = entrustNumber;
    }

    public Integer getSellNumber() {
        return sellNumber;
    }

    public void setSellNumber(Integer sellNumber) {
        this.sellNumber = sellNumber;
    }

    public Date getSellTime() {
        return sellTime;
    }

    public void setSellTime(Date sellTime) {
        this.sellTime = sellTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getResidueDelayDays() {
        return residueDelayDays;
    }

    public void setResidueDelayDays(Integer residueDelayDays) {
        this.residueDelayDays = residueDelayDays;
    }

    public Integer getBackDelayDays() {
        return backDelayDays;
    }

    public void setBackDelayDays(Integer backDelayDays) {
        this.backDelayDays = backDelayDays;
    }

    public BigDecimal getBackDelayMoney() {
        return backDelayMoney;
    }

    public void setBackDelayMoney(BigDecimal backDelayMoney) {
        this.backDelayMoney = backDelayMoney;
    }

    public Integer getAlreadyDelayDays() {
        return alreadyDelayDays;
    }

    public void setAlreadyDelayDays(Integer alreadyDelayDays) {
        this.alreadyDelayDays = alreadyDelayDays;
    }

    public BigDecimal getDelayMoneyUnitPrice() {
        return delayMoneyUnitPrice;
    }

    public void setDelayMoneyUnitPrice(BigDecimal delayMoneyUnitPrice) {
        this.delayMoneyUnitPrice = delayMoneyUnitPrice;
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
                ", buyTime=" + buyTime +
                ", buyPriceTotal=" + buyPriceTotal +
                ", sellPrice=" + sellPrice +
                ", sellPriceTotal=" + sellPriceTotal +
                ", orderStatus=" + orderStatus +
                ", profit=" + profit +
                ", profitRate=" + profitRate +
                ", strategyOwnMoney=" + strategyOwnMoney +
                ", strategyMoney=" + strategyMoney +
                ", stopEarnMoney=" + stopEarnMoney +
                ", stopLossMoney=" + stopLossMoney +
                ", serviceMoney=" + serviceMoney +
                ", delayMoneyUnitPrice=" + delayMoneyUnitPrice +
                ", delayMoney=" + delayMoney +
                ", delayDays=" + delayDays +
                ", alreadyDelayDays=" + alreadyDelayDays +
                ", residueDelayDays=" + residueDelayDays +
                ", backDelayDays=" + backDelayDays +
                ", backDelayMoney=" + backDelayMoney +
                ", delayEndTime=" + delayEndTime +
                ", entrustPrice=" + entrustPrice +
                ", entrustNumber=" + entrustNumber +
                ", sellNumber=" + sellNumber +
                ", sellTime=" + sellTime +
                ", cancelTime=" + cancelTime +
                '}';
    }

}
