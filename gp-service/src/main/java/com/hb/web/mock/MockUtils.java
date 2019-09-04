package com.hb.web.mock;

import com.hb.remote.model.StockModel;

import java.math.BigDecimal;

public class MockUtils {

    public static StockModel mockStock() {
        StockModel stockModel = new StockModel();
        stockModel.setStockCode("600001");
        stockModel.setStockName("浦发银行");
        stockModel.setCurrentPrice(new BigDecimal("15"));
        return stockModel;
    }

}
