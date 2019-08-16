package com.hb.web.api;

import java.util.List;

/**
 * ========== 导出excel接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IExportExcelService.java, v1.0
 * @date 2019年07月16日 21时32分
 */
public interface IExportExcelService {

    /**
     * ########## 填充数据 ##########
     *
     * @param queryResult 查询数据库的结果
     * @return 组装后的数据
     */
    List<List<Object>> fillData(Object queryResult);

    /**
     * ########## 获取sheetName ##########
     *
     * @return sheetName
     */
    String getSheetName();

    /**
     * ########## 获取sheetName ##########
     *
     * @return sheetName
     */
    String getExcelType();

    /**
     * ########## 标题 ##########
     *
     * @return 标题集合
     */
    List<String> getTitles();

}
