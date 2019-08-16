package com.hb.web.impl;

import com.hb.facade.entity.AgentDO;
import com.hb.facade.enumutil.AgentLevelEnum;
import com.hb.facade.enumutil.RealAuthStatusEnum;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.api.IAgentReportService;
import com.hb.web.api.IAgentService;
import com.hb.web.api.IExportExcelService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ========== 代理商报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentReportService.java, v1.0
 * @date 2019年07月16日 22时00分
 */
@Service
public class AgentReportServiceImpl extends AbstractExportExcelService implements IExportExcelService, IAgentReportService {

    @Autowired
    private IAgentService iAgentService;

    @Override
    public void exportExcel(HttpServletResponse response, AgentDO agentDO, Integer pageNum, Integer pageSize) {
        List<AgentDO> agentList = iAgentService.findAgentList(agentDO, pageNum, pageSize);
        doExport(response, agentList);
    }

    @Override
    public List<List<Object>> fillData(Object queryResult) {
        List<AgentDO> agentList = queryResult == null ? null : (List<AgentDO>) queryResult;
        if (CollectionUtils.isEmpty(agentList)) {
            return null;
        }
        List<List<Object>> dataList = new ArrayList<>();
        for (AgentDO agentDO : agentList) {
            List<Object> list = new ArrayList<>();
            list.add(agentDO.getAgentId());
            list.add(agentDO.getAgentName());
            list.add(AgentLevelEnum.stateOf(agentDO.getAgentLevel()));
            list.add(agentDO.getBankName());
            list.add(agentDO.getBankNo());
            list.add(agentDO.getIdCardNo());
            list.add(RealAuthStatusEnum.stateOf(agentDO.getRealAuthStatus()));
            list.add(agentDO.getMobile());
            list.add(DateUtils.date2str(agentDO.getCreateTime(), DateUtils.DEFAULT_FORMAT));
            dataList.add(list);
        }
        return dataList;
    }

    @Override
    public String getSheetName() {
        return "代理商报表";
    }

    @Override
    public String getExcelType() {
        return "xls";
    }

    @Override
    public List<String> getTitles() {
        List<String> titleList = Arrays.asList("代理商ID", "代理商名称", "代理商等级", "银行名称", "银行卡号", "身份证号", "实名认证状态", "手机号", "创建时间");
        return titleList;
    }
}
