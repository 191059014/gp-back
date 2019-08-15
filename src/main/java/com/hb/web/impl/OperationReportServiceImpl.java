package com.hb.web.impl;

import com.hb.web.api.IExportExcelService;
import com.hb.web.api.IOperationReportService;
import com.hb.entity.AgentDO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ========== 运营报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentReportService.java, v1.0
 * @date 2019年07月16日 22时00分
 */
@Service
public class OperationReportServiceImpl extends AbstractExportExcelService implements IExportExcelService, IOperationReportService {

    /**
     * ########## 导出excel ##########
     *
     * @param response 响应对象
     * @param agentDO  查询条件
     */
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
        return "运营报表";
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
