package com.hb.web.tool;

import com.hb.web.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * ========== excel工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.tool.ExcelTools.java, v1.0
 * @date 2019年07月16日 20时55分
 */
public class ExcelTools {

    /**
     * ########## 生成excel ##########
     *
     * @param sheetName sheet名
     * @param style     Excel类型
     * @param titles    标题串
     * @return Workbook
     */
    public static Workbook generateWorkbook(String sheetName, String style, List<String> titles, List<List<Object>> data) {
        Workbook workbook;
        if ("XLS".equals(style.toUpperCase())) {
            workbook = new HSSFWorkbook();
        } else {
            workbook = new XSSFWorkbook();
        }
        // 生成一个表格
        Sheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 36);
        // 设置标题样式
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        /*
         * 创建标题行
         */
        Row row = sheet.createRow(0);
        for (int i = 0; i < titles.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(StringUtils.convertNull(titles.get(i)));
        }
        /*
         * 填充内容
         */
        CellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setAlignment(HorizontalAlignment.RIGHT);
        for (int j = 0; j < data.size(); j++) {
            row = sheet.createRow(j + 1);
            List<Object> cellValueList = data.get(j);
            for (int k = 0; k < titles.size(); k++) {
                Cell cell = row.createCell(k);
                cell.setCellStyle(contentCellStyle);
                cell.setCellValue(StringUtils.convertNull(cellValueList.get(k)));
            }
        }

        return workbook;
    }

}
