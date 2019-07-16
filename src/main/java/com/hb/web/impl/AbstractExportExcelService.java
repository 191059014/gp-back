package com.hb.web.impl;

import com.hb.web.api.IExportExcelService;
import com.hb.web.tool.ExcelTools;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.util.DateUtils;
import com.hb.web.util.LogUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

/**
 * ========== 导出excel抽象类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AbstractExportExcelService.java, v1.0
 * @date 2019年07月16日 21时35分
 */
public abstract class AbstractExportExcelService implements IExportExcelService {

    /**
     * the common logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExportExcelService.class);

    /**
     * ########## 导出excel ##########
     *
     * @param response 响应对象
     * @param dataObj  数据
     */
    public void doExport(HttpServletResponse response, Object dataObj) {
        /**
         * 组装数据
         */
        List<List<Object>> dataList = fillData(dataObj);
        String sheetName = getSheetName();
        String excelType = getExcelType();
        List<String> titles = getTitles();
        /**
         * 生成excel
         */
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String dataStr = DateUtils.getCurrentDateStr(DateUtils.YYYYMMDD);
        response.setHeader("Content-Disposition", "attachment;filename=" + dataStr + "_template.xls");
        try {
            Workbook workbook = ExcelTools.generateWorkbook(sheetName, excelType, titles, dataList);
            OutputStream outputStream = response.getOutputStream();
            workbook.write(response.getOutputStream());
            workbook.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            LOGGER.error("导出excel异常：{}", LogUtils.getStackTrace(e));
        }
    }

}
