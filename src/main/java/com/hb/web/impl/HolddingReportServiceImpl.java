package com.hb.web.impl;

import com.hb.web.api.IExportExcelService;
import com.hb.web.api.IHolddingReportService;
import com.hb.entity.AgentDO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ========== 持仓中报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentReportService.java, v1.0
 * @date 2019年07月16日 22时00分
 */
@Service
public class HolddingReportServiceImpl extends AbstractExportExcelService implements IExportExcelService, IHolddingReportService {

    @Override
    public void exportExcel(HttpServletResponse response, AgentDO agentDO) {
        doExport(response, agentDO);
    }

    @Override
    public List<List<Object>> fillData(Object queryResult) {
        return null;
    }

    @Override
    public String getSheetName() {
        return "持仓中报表";
    }

    @Override
    public String getExcelType() {
        return "xls";
    }

    @Override
    public List<String> getTitles() {
        return null;
    }
}
