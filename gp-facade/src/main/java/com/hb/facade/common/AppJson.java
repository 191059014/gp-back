package com.hb.facade.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class AppJson implements Serializable {

    private static final long serialVersionUID = 9172392464944367137L;
    // 涨停最大百分比
    private BigDecimal stopMaxPercent;
    // 跌停最大百分比
    private BigDecimal stopMinPercent;
    // 服务费百分比
    private BigDecimal serviceMoneyPercent;
    // 递延费百分比
    private BigDecimal delayMoneyPercent;
    // 节假日
    private Set<String> specialHoliday;

    public BigDecimal getStopMaxPercent() {
        return stopMaxPercent;
    }

    public void setStopMaxPercent(BigDecimal stopMaxPercent) {
        this.stopMaxPercent = stopMaxPercent;
    }

    public BigDecimal getStopMinPercent() {
        return stopMinPercent;
    }

    public void setStopMinPercent(BigDecimal stopMinPercent) {
        this.stopMinPercent = stopMinPercent;
    }

    public BigDecimal getServiceMoneyPercent() {
        return serviceMoneyPercent;
    }

    public void setServiceMoneyPercent(BigDecimal serviceMoneyPercent) {
        this.serviceMoneyPercent = serviceMoneyPercent;
    }

    public BigDecimal getDelayMoneyPercent() {
        return delayMoneyPercent;
    }

    public void setDelayMoneyPercent(BigDecimal delayMoneyPercent) {
        this.delayMoneyPercent = delayMoneyPercent;
    }

    public Set<String> getSpecialHoliday() {
        return specialHoliday;
    }

    public void setSpecialHoliday(Set<String> specialHoliday) {
        this.specialHoliday = specialHoliday;
    }

    @Override
    public String toString() {
        return "AppJson{" +
                "stopMaxPercent=" + stopMaxPercent +
                ", stopMinPercent=" + stopMinPercent +
                ", serviceMoneyPercent=" + serviceMoneyPercent +
                ", delayMoneyPercent=" + delayMoneyPercent +
                ", specialHoliday=" + specialHoliday +
                '}';
    }
}
