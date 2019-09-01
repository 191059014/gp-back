package com.hb.web.tool;

import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.web.container.SystemConfig;

import java.math.BigDecimal;

/**
 * 计算工具类
 */
public class CalcTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcTools.class);

    /**
     * 计算每天产生的递延金 = 持仓市值  * 递延费的比例
     *
     * @param strategyMoney 持仓市值
     * @return 递延金
     */
    public static BigDecimal calcDelayMoneyPerDay(BigDecimal strategyMoney) {
        BigDecimal delayMoney = BigDecimalUtils.multiply(SystemConfig.getAppJson().getDelayMoneyPercent(), strategyMoney);
        LOGGER.info("每天产生的递延金：{}", delayMoney);
        return delayMoney;
    }

    /**
     * 计算指定天数产生的递延金 = 持仓市值  * 递延费的比例  * 天数
     *
     * @param strategyMoney 持仓市值
     * @param delayDays     递延天数
     * @return 递延金
     */
    public static BigDecimal calcDelayMoney(BigDecimal strategyMoney, Integer delayDays) {
        BigDecimal delayMoney = BigDecimalUtils.multiply(calcDelayMoneyPerDay(strategyMoney), new BigDecimal(delayDays));
        LOGGER.info("{}天产生的递延金：{}", delayDays, delayMoney);
        return delayMoney;
    }

    /**
     * 计算订单利润
     *
     * @param buyPrice  买入价格
     * @param sellPrice 卖出价格
     * @param buyNumber 买入股数
     * @return 总利润
     */
    public static BigDecimal calcOrderProfit(BigDecimal buyPrice, BigDecimal sellPrice, Integer buyNumber) {
        BigDecimal unitProfit = BigDecimalUtils.subtract(sellPrice, buyPrice);
        BigDecimal totalProfit = BigDecimalUtils.multiply(unitProfit, new BigDecimal(buyNumber));
        LOGGER.info("利润：{}", totalProfit);
        return totalProfit;
    }

    /**
     * 计算订单盈亏率
     *
     * @param strategyMoney 持仓市值
     * @return 总利润
     */
    public static BigDecimal calcOrderProfitRate(BigDecimal profit, BigDecimal strategyMoney) {
        BigDecimal profitRate = BigDecimalUtils.divide(profit, strategyMoney);
        LOGGER.info("利润：{}", profitRate);
        return profitRate;
    }

    /**
     * 计算服务费
     *
     * @param strategyMoney 持仓市值
     * @return 服务费
     */
    public static BigDecimal calcServiceMoney(BigDecimal strategyMoney) {
        BigDecimal serviceMoney = BigDecimalUtils.multiply(SystemConfig.getAppJson().getServiceMoneyPercent(), strategyMoney);
        LOGGER.info("服务费：{}", serviceMoney);
        return serviceMoney;
    }

}
