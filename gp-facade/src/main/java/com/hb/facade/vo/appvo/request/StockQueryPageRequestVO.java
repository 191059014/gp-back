package com.hb.facade.vo.appvo.request;

/**
 * ========== 根据股票代码模糊搜索股票信息-请求参数 ==========
 *
 * @author Mr.huang
 * @version com.hb.facade.vo.appvo.request.StockQueryPageRequestVO.java, v1.0
 * @date 2019年08月14日 00时51分
 */
public class StockQueryPageRequestVO extends AppPages {

    private static final long serialVersionUID = -1244937184626484440L;

    private String queryText;

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    @Override
    public String toString() {
        return "StockQueryPageRequestVO{" +
                "queryText='" + queryText + '\'' +
                "} " + super.toString();
    }
}
