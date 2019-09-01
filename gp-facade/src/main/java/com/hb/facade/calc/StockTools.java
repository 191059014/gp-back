package com.hb.facade.calc;

import com.hb.facade.common.SystemConfig;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.DateUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 股票工具类
 */
public class StockTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockTools.class);

    /**
     * 计算每天产生的递延金 = 持仓市值  * 递延费的比例
     *
     * @param strategyMoney 持仓市值
     * @return 递延金
     */
    public static BigDecimal calcDelayMoneyPerDay(BigDecimal strategyMoney, BigDecimal delayMoneyPercent) {
        BigDecimal delayMoney = BigDecimalUtils.multiply(delayMoneyPercent, strategyMoney);
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
    public static BigDecimal calcDelayMoney(BigDecimal strategyMoney, Integer delayDays, BigDecimal delayMoneyPercent) {
        BigDecimal delayMoney = BigDecimalUtils.multiply(calcDelayMoneyPerDay(strategyMoney, delayMoneyPercent), new BigDecimal(delayDays));
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
        BigDecimal unitProfit = BigDecimalUtils.subtract(sellPrice, buyPrice, BigDecimalUtils.TEN_SCALE);
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
    public static BigDecimal calcServiceMoney(BigDecimal strategyMoney, BigDecimal serviceMoneyPercent) {
        BigDecimal serviceMoney = BigDecimalUtils.multiply(serviceMoneyPercent, strategyMoney);
        LOGGER.info("服务费：{}", serviceMoney);
        return serviceMoney;
    }

    /**
     * ########## 判断股票是不是开市时间 ##########
     *
     * @return boolean
     */
    public static boolean stockOnLine() {
        Date currentDate = DateUtils.getCurrentDate();
        if (isSpecialHoliday(currentDate)) return false;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        int week = c.get(Calendar.DAY_OF_WEEK);
        if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
            return false;
        }
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String nowStr = hour + "" + minute;
        int now = Integer.parseInt(nowStr);
        if ((now >= 930 && now <= 1130) || (now >= 1300 && now <= 1500)) {
            return true;
        }
        return false;
    }

    /**
     * 根据下单时间和递延天数计算卖出的交易日（排除节假日）
     *
     * @param orderTime 下单时间
     * @param delayDays 递延天数
     * @return 是否可卖出
     */
    public static boolean isSellDate(Date orderTime, int delayDays) {
        Date date = calcSellDate(orderTime, delayDays);
        int daysBetween = DateUtils.getDaysBetween(new Date(), date);
        if (daysBetween == 0) {
            return true;
        }
        return false;
    }

    /**
     * 计算退还的递延金天数
     *
     * @param orderTime 下单时间
     * @param delayDays 递延天数
     * @return 退还的递延金天数
     */
    public static int calcBackDays(Date orderTime, int delayDays) {
        Date date = null;
        int j = 0;
        for (int i = 0; i < 100; i++) {
            date = DateUtils.addDays(orderTime, i + 1);
            if (!isSpecialHoliday(date)) {
                j++;
            }
            if (DateUtils.getDaysBetween(new Date(), date) == 0) {
                break;
            }
        }
        return delayDays - j;
    }

    /**
     * 计算卖出的日期
     *
     * @param orderTime 下单时间
     * @param delayDays 递延天数
     * @return 卖出的日期
     */
    private static Date calcSellDate(Date orderTime, int delayDays) {
        Date date = null;
        int j = 0;
        for (int i = 0; i < 100; i++) {
            date = DateUtils.addDays(orderTime, i + 1);
            if (!isSpecialHoliday(date)) {
                j++;
            }
            if (delayDays == j) {
                break;
            }
        }
        return date;
    }

    /**
     * 判断是不是周末或者 节假日
     *
     * @param date 时间
     * @return 是否是周末或者节假日
     */
    private static boolean isSpecialHoliday(Date date) {
        if (DateUtils.isWeekend(date) || SystemConfig.isSpecialHoliday(date)) {
            return true;
        }
        return false;
    }

}
