package com.hb.facade.calc;

import com.hb.facade.common.SystemConfig;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.BigDecimalUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.unic.util.util.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
        LOGGER.info("利润=（卖出价格-买入价格）*买入股数=({}-{})*{}={}", sellPrice, buyPrice, buyNumber, totalProfit);
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
        LOGGER.info("利润=利润/建仓市值={}/{}={}", profit, strategyMoney, profitRate);
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
        if (true) {
            return true;
        }
        Date currentDate = DateUtils.getCurrentDate();
        LOGGER.info("stockOnLine#当前时间：{}", DateUtils.date2str(currentDate, DateUtils.DEFAULT_FORMAT));
        if (isSpecialHoliday(currentDate)) {
            LOGGER.info("stockOnLine#当前时间是非交易日");
            return false;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String nowStr = StringUtils.fillZero(hour + "", 2) + StringUtils.fillZero(minute + "", 2);
        int now = Integer.parseInt(nowStr);
        LOGGER.info("stockOnLine#当前时间数字：{}", now);
        if ((now >= 930 && now <= 1130) || (now >= 1300 && now <= 1455)) {
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
        System.out.println("下单时间：" + DateUtils.date2str(orderTime, DateUtils.FORMAT_YMD) + "，递延天数：" + delayDays);
        Date date = null;
        int j = 0;
        for (int i = 0; i < 100; i++) {
            date = DateUtils.addDays(orderTime, i + 1);
            if (!isSpecialHoliday(date)) {
                j++;
            } else {
                System.out.println("周末或者节假日：" + DateUtils.date2str(date, DateUtils.FORMAT_YMD));
            }
            if (DateUtils.getDaysBetween(new Date(), date) == 0) {
                break;
            }
        }
        return delayDays - j - 1;
    }

    /**
     * 计算卖出的日期
     *
     * @param orderTime 下单时间
     * @param delayDays 递延天数
     * @return 卖出的日期
     */
    public static Date calcSellDate(Date orderTime, int delayDays) {
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 50);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 判断今天是否是买入当天
     *
     * @param buyTime 买入时间
     * @return true为是
     */
    public static boolean todayIsBuyDate(Date buyTime) {
        // 当前时间
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        int nowDate = c1.get(Calendar.DATE);
        // 买入时间
        Calendar c2 = Calendar.getInstance();
        c2.setTime(buyTime);
        int buyDate = c2.get(Calendar.DATE);
        if (nowDate == buyDate) {
            return true;
        }
        return false;
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

    public static void main(String[] args) throws ParseException {
//        stockOnLine();
        System.out.println(needObtainStockInfoFromCache());
    }

    /**
     * 计算纯利润
     *
     * @param profit       利润
     * @param serviceMoney 服务费
     * @param delayMoney   递延费
     * @return 纯利润
     */
    public static BigDecimal calcOrderNetProfit(BigDecimal profit, BigDecimal serviceMoney, BigDecimal delayMoney) {
        return BigDecimalUtils.subtractAll(BigDecimalUtils.DEFAULT_SCALE, profit, serviceMoney, delayMoney);
    }

    /**
     * ########## 判断当前时间是否取缓存内的行情数据 ##########
     *
     * @return boolean
     */
    public static boolean needObtainStockInfoFromCache() {
        Date currentDate = DateUtils.getCurrentDate();
        LOGGER.info("needObtainStockInfoFromCache#当前时间：{}", DateUtils.date2str(currentDate, DateUtils.DEFAULT_FORMAT));
        if (isSpecialHoliday(currentDate)) {
            LOGGER.info("needObtainStockInfoFromCache#当前时间是非交易日，需要从缓存获取数据");
            return true;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        String nowStr = StringUtils.fillZero(hour + "", 2) + StringUtils.fillZero(minute + "", 2);
        int now = Integer.parseInt(nowStr);
        LOGGER.info("needObtainStockInfoFromCache#当前时间数字：{}", now);
        if (now < 930 || (now > 1133 & now < 1300) || now > 1503) {
            return true;
        }
        return false;
    }

}
