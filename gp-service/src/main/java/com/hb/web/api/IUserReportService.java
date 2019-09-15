package com.hb.web.api;

import com.hb.facade.entity.UserDO;

import javax.servlet.http.HttpServletResponse;

/**
 * ========== 用户报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IUserReportService.java, v1.0
 * @date 2019年07月16日 22时21分
 */
public interface IUserReportService {
    /**
     * ########## 导出excel ##########
     *
     * @param response 响应对象
     * @param userDO   查询条件
     * @param pageNum  当前页数
     * @param pageSize 每页条数
     */
    void exportExcel(HttpServletResponse response, UserDO userDO, Integer pageNum, Integer pageSize);

}
