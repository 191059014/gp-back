package com.hb.web.impl;

import com.hb.facade.calc.StockTools;
import com.hb.facade.entity.AgentDO;
import com.hb.facade.entity.OrderDO;
import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.OrderStatusEnum;
import com.hb.facade.vo.webvo.request.HoldReportQueryRequestVO;
import com.hb.facade.vo.webvo.response.AlreadySettledResponseVO;
import com.hb.unic.util.helper.PageHelper;
import com.hb.unic.util.util.CloneUtils;
import com.hb.unic.util.util.DateUtils;
import com.hb.web.api.IAlreadySettledReportService;
import com.hb.web.api.IExportExcelService;
import com.hb.web.api.IOrderService;
import com.hb.web.api.IUserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ========== 已结算报表 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.AgentReportService.java, v1.0
 * @date 2019年07月16日 22时00分
 */
@Service
public class AlreadySettledReportServiceImpl extends AbstractExportExcelService implements IExportExcelService, IAlreadySettledReportService {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @Override
    public List<Map<String, Object>> findHoldReportPages(HoldReportQueryRequestVO requestVO, Integer pageNum, Integer pageSize) {
        return getOrderListPages(requestVO, pageNum, pageSize);
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
            AlreadySettledResponseVO orderInfo = (AlreadySettledResponseVO) map.get("order");
            List<Object> list = new ArrayList<>();
            list.add(orderInfo.getOrderId());
            list.add(userDO.getUserName());
            list.add(orderInfo.getStockCode());
            list.add(orderInfo.getStockName());
            list.add(orderInfo.getBuyNumber());
            list.add(orderInfo.getBuyPrice());
            list.add(DateUtils.date2str(orderInfo.getBuyTime(), DateUtils.DEFAULT_FORMAT));
            list.add(orderInfo.getStrategyOwnMoney());
            list.add(orderInfo.getStrategyMoney());
            list.add(orderInfo.getStopEarnMoney());
            list.add(orderInfo.getStopLossMoney());
            list.add(orderInfo.getServiceMoney());
            list.add(orderInfo.getDelayMoney());
            list.add(orderInfo.getAlreadyDelayDays());
            list.add(DateUtils.date2str(orderInfo.getDelayEndTime(), DateUtils.DEFAULT_FORMAT));
            list.add(orderInfo.getSellPrice());
            list.add(DateUtils.date2str(orderInfo.getSellTime(), DateUtils.DEFAULT_FORMAT));
            list.add(orderInfo.getBackDelayMoney());
            list.add(orderInfo.getProfit());
            list.add(orderInfo.getProfitRate());
            list.add(orderInfo.getNetProfit());
            dataList.add(list);
        }
        return dataList;
    }

    @Override
    public String getSheetName() {
        return "已结算报表";
    }

    @Override
    public String getExcelType() {
        return "xls";
    }

    @Override
    public List<String> getTitles() {
        List<String> titleList = Arrays.asList("订单ID", "客户姓名", "股票代码", "股票名称", "买入股数", "买入价格", "买入时间"
                , "策略本金", "策略金额", "止盈价格", "止损价格", "信息服务费", "递延金", "已递延天数", "递延到期时间"
                , "卖出价格", "卖出时间", "退还递延金额", "利润", "盈亏率", "净利润");
        return titleList;
    }

    /**
     * 分页查询已结算报表数据
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
        userQuery.setUnit(requestVO.getUnit());
        userQuery.setInviterMobile(requestVO.getInviterMobile());
        List<UserDO> userList = iUserService.findUserList(userQuery, null, null);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        Map<String, UserDO> userDOMap = userList.stream().collect(Collectors.toMap(UserDO::getUserId, user -> user, (k1, k2) -> k2));
        Set<Integer> orderStatusSet = new HashSet<>();
        orderStatusSet.add(OrderStatusEnum.ALREADY_SELL.getValue());
        Set<String> userIdSet = userDOMap == null ? null : userDOMap.keySet();
        List<OrderDO> orderList = iOrderService.findByUserIdSetAndOrderStatusAndTimeBetweenPages(userIdSet, orderStatusSet, requestVO.getOrderTimeStart(), requestVO.getOrderTimeEnd(), PageHelper.getStartRow(pageNum, pageSize), pageSize);
        if (CollectionUtils.isEmpty(orderList)) {
            return null;
        }
        List<Map<String, Object>> queryDataList = new ArrayList<>();
        orderList.forEach(orderDO -> {
            Map<String, Object> map = new HashedMap();
            map.put("user", userDOMap.get(orderDO.getUserId()));
            AlreadySettledResponseVO responseVO = new AlreadySettledResponseVO();
            BeanUtils.copyProperties(orderDO, responseVO);
            responseVO.setNetProfit(StockTools.calcOrderNetProfit(orderDO.getProfit(), orderDO.getServiceMoney(), orderDO.getDelayMoney()));
            map.put("order", responseVO);
            queryDataList.add(map);
        });
        return queryDataList;
    }

    @Override
    public int findCount(HoldReportQueryRequestVO requestVO) {
        UserDO userQuery = new UserDO();
        userQuery.setUserName(requestVO.getUserName());
        userQuery.setMobile(requestVO.getMobile());
        userQuery.setUnit(requestVO.getUnit());
        userQuery.setInviterMobile(requestVO.getInviterMobile());
        List<UserDO> userList = iUserService.findUserList(userQuery, null, null);
        if (CollectionUtils.isEmpty(userList)) {
            return 0;
        }
        Map<String, UserDO> userDOMap = userList.stream().collect(Collectors.toMap(UserDO::getUserId, user -> user, (k1, k2) -> k2));
        Set<Integer> orderStatusSet = new HashSet<>();
        orderStatusSet.add(OrderStatusEnum.ALREADY_SELL.getValue());
        Set<String> userIdSet = userDOMap == null ? null : userDOMap.keySet();
        int count = iOrderService.findByUserIdSetAndOrderStatusAndTimeBetweenPagesCount(userIdSet, orderStatusSet, requestVO.getOrderTimeStart(), requestVO.getOrderTimeEnd());
        return count;
    }

}
