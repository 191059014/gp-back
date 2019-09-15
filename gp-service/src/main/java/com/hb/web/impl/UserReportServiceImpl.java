package com.hb.web.impl;

import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.RealAuthStatusEnum;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.api.IExportExcelService;
import com.hb.web.api.IUserReportService;
import com.hb.web.api.IUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ========== 用户报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentReportService.java, v1.0
 * @date 2019年07月16日 22时00分
 */
@Service
public class UserReportServiceImpl extends AbstractExportExcelService implements IExportExcelService, IUserReportService {

    @Autowired
    private IUserService iUserService;

    @Override
    public void exportExcel(HttpServletResponse response, UserDO userDO, Integer pageNum, Integer pageSize) {
        List<UserDO> userList = iUserService.findUserList(userDO, pageNum, pageSize);
        doExport(response, userList);
    }

    @Override
    public List<List<Object>> fillData(Object queryResult) {
        List<UserDO> userList = queryResult == null ? null : (List<UserDO>) queryResult;
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        List<List<Object>> dataList = new ArrayList<>();
        for (UserDO userDo : userList) {
            List<Object> list = new ArrayList<>();
            list.add(userDo.getUserId());
            list.add(userDo.getUserName());
            list.add(userDo.getBankName());
            list.add(userDo.getBankNo());
            list.add(userDo.getIdCardNo());
            list.add(RealAuthStatusEnum.stateOf(userDo.getRealAuthStatus()));
            list.add(userDo.getMobile());
            list.add(DateUtils.date2str(userDo.getCreateTime(), DateUtils.DEFAULT_FORMAT));
            dataList.add(list);
        }
        return dataList;
    }

    @Override
    public String getSheetName() {
        return "客户报表";
    }

    @Override
    public String getExcelType() {
        return "xls";
    }

    @Override
    public List<String> getTitles() {
        List<String> titleList = Arrays.asList("客户ID", "客户姓名", "银行名称", "银行卡号", "身份证号", "实名认证状态", "手机号", "创建时间");
        return titleList;
    }

}
