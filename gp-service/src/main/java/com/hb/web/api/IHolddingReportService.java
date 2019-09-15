package com.hb.web.api;

import com.hb.facade.vo.webvo.request.HoldReportQueryRequestVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * ========== 持仓中报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IHolddingReportService.java, v1.0
 * @date 2019年07月16日 22时20分
 */
public interface IHolddingReportService {
    /**
     * ########## 导出excel ##########
     *
     * @param response  响应对象
     * @param requestVO 查询条件
     * @param pageNum   当前页数
     * @param pageSize  每页条数
     */
    void exportExcel(HttpServletResponse response, HoldReportQueryRequestVO requestVO, Integer pageNum, Integer pageSize);

    /**
     * ########## 分页查询 ##########
     *  @param requestVO 查询条件
     * @param pageNum   当前页数
     * @param pageSize  每页条数
     */
    List<Map<String, Object>> findHoldReportPages(HoldReportQueryRequestVO requestVO, Integer pageNum, Integer pageSize);

    /**
     * ########## 查询总条数 ##########
     *
     * @param requestVO 查询条件
     */
    int findCount(HoldReportQueryRequestVO requestVO);
}
