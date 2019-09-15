package com.hb.web.impl;

import com.hb.facade.entity.OrderDO;
import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.OrderStatusEnum;
import com.hb.facade.vo.webvo.request.HoldReportQueryRequestVO;
import com.hb.unic.util.helper.PageHelper;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.api.IExportExcelService;
import com.hb.web.api.IHolddingReportService;
import com.hb.web.api.IOrderService;
import com.hb.web.api.IUserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ========== 持仓中报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentReportService.java, v1.0
 * @date 2019年07月16日 22时00分
 */
@Service
public class HolddingReportServiceImpl extends AbstractExportExcelService implements IExportExcelService, IHolddingReportService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @Override
    public List<Map<String, Object>> findHoldReportPages(HoldReportQueryRequestVO requestVO, Integer pageNum, Integer pageSize) {
        return getOrderListPages(requestVO, pageNum, pageSize);
    }

    @Override
    public int findCount(HoldReportQueryRequestVO requestVO) {
        UserDO userQuery = new UserDO();
        userQuery.setUserName(requestVO.getUserName());
        userQuery.setMobile(requestVO.getMobile());
        List<UserDO> userList = iUserService.findUserList(userQuery, null, null);
        if (CollectionUtils.isEmpty(userList)) {
            return 0;
        }
        Map<String, UserDO> userDOMap = userList.stream().collect(Collectors.toMap(UserDO::getUserId, user -> user, (k1, k2) -> k2));
        Set<Integer> orderStatusSet = new HashSet<>();
        orderStatusSet.add(OrderStatusEnum.IN_THE_POSITION.getValue());
        Set<String> userIdSet = userDOMap == null ? null : userDOMap.keySet();
        int count = iOrderService.findByUserIdSetAndOrderStatusAndTimeBetweenPagesCount(userIdSet, orderStatusSet, requestVO.getOrderTimeStart(), requestVO.getOrderTimeEnd());
        return count;
    }

    @Override
    public void exportExcel(HttpServletResponse response, HoldReportQueryRequestVO requestVO, Integer pageNum, Integer pageSize) {
        doExport(response, getOrderListPages(requestVO, pageNum, pageSize));
    }

    @Override
    public List<List<Object>> fillData(Object queryResult) {
        List<Map<String, Object>> queryDataList = queryResult == null ? null : (List<Map<String, Object>>) queryResult;
        if (CollectionUtils.isEmpty(queryDataList)) {
            return null;
        }
        List<List<Object>> dataList = new ArrayList<>();
        for (Map<String, Object> map : queryDataList) {
            UserDO userDO = (UserDO) map.get("user");
            OrderDO orderDO = (OrderDO) map.get("order");
            List<Object> list = new ArrayList<>();
            list.add(orderDO.getOrderId());
            list.add(userDO.getUserName());
            list.add(orderDO.getStockCode());
            list.add(orderDO.getStockName());
            list.add(orderDO.getBuyNumber());
            list.add(orderDO.getBuyPrice());
            list.add(DateUtils.date2str(orderDO.getBuyTime(), DateUtils.DEFAULT_FORMAT));
            list.add(orderDO.getStrategyOwnMoney());
            list.add(orderDO.getStrategyMoney());
            list.add(orderDO.getStopEarnMoney());
            list.add(orderDO.getStopLossMoney());
            list.add(orderDO.getServiceMoney());
            list.add(orderDO.getDelayMoney());
            list.add(orderDO.getAlreadyDelayDays());
            list.add(DateUtils.date2str(orderDO.getDelayEndTime(), DateUtils.DEFAULT_FORMAT));
            dataList.add(list);
        }
        return dataList;
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
        List<String> titleList = Arrays.asList("订单ID", "客户姓名", "股票代码", "股票名称", "买入股数", "买入价格", "买入时间"
                , "策略本金", "策略金额", "止盈价格", "止损价格", "信息服务费", "递延金", "已递延天数", "递延到期时间");
        return titleList;
    }

    /**
     * 分页查询持仓中报表数据
     *
     * @param requestVO 查询参数
     * @param pageNum   当前页
     * @param pageSize  每页数量
     * @return 订单信息
     */
    private List<Map<String, Object>> getOrderListPages(HoldReportQueryRequestVO requestVO, Integer pageNum, Integer pageSize) {
        UserDO userQuery = new UserDO();
        userQuery.setUserName(requestVO.getUserName());
        userQuery.setMobile(requestVO.getMobile());
        List<UserDO> userList = iUserService.findUserList(userQuery, null, null);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        Map<String, UserDO> userDOMap = userList.stream().collect(Collectors.toMap(UserDO::getUserId, user -> user, (k1, k2) -> k2));
        Set<Integer> orderStatusSet = new HashSet<>();
        orderStatusSet.add(OrderStatusEnum.IN_THE_POSITION.getValue());
        Set<String> userIdSet = userDOMap == null ? null : userDOMap.keySet();
        List<OrderDO> orderList = iOrderService.findByUserIdSetAndOrderStatusAndTimeBetweenPages(userIdSet, orderStatusSet, requestVO.getOrderTimeStart(), requestVO.getOrderTimeEnd(), PageHelper.getStartRow(pageNum, pageSize), pageSize);
        if (CollectionUtils.isEmpty(orderList)) {
            return null;
        }
        List<Map<String, Object>> queryDataList = new ArrayList<>();
        orderList.forEach(orderDO -> {
            Map<String, Object> map = new HashedMap();
            map.put("user", userDOMap.get(orderDO.getUserId()));
            map.put("order", orderDO);
            queryDataList.add(map);
        });
        return queryDataList;
    }

}
