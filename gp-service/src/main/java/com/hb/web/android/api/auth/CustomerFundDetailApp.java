package com.hb.web.android.api.auth;

import com.hb.facade.entity.CustomerFundDetailDO;
import com.hb.facade.entity.UserDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.android.base.BaseApp;
import com.hb.web.api.ICustomerFundDetailService;
import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.web.util.LogUtils;
import com.hb.facade.vo.appvo.request.QueryCustomerFundDetailRequestVO;
import com.hb.facade.vo.appvo.request.QueryCustomerFundDetailResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * ========== 用户资金流水 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.auth.CustomerFundDetailApp.java, v1.0
 * @date 2019年07月22日 20时55分
 */
@Api(tags = "[APP]用户资金流水")
@RestController
@RequestMapping("app/auth/customerFundDetail")
public class CustomerFundDetailApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerFundApp.class);

    @Autowired
    private ICustomerFundDetailService iCustomerFundDetailService;

    @ApiOperation(value = "查询用户资金流水")
    @PostMapping("/queryCustomerFundDetail")
    public AppResultModel<QueryCustomerFundDetailResponseVO> queryCustomerFundDetail(@RequestBody QueryCustomerFundDetailRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("查询用户资金流水，入参：{}"), requestVO);
        QueryCustomerFundDetailResponseVO responseVO = new QueryCustomerFundDetailResponseVO();
        Date beginTime = requestVO.getTimeBegin() == null ? null : new Date(requestVO.getTimeBegin());
        Date endTime = requestVO.getTimeEnd() == null ? null : new Date(requestVO.getTimeEnd());
        UserDO userCache = getCurrentUserCache();
        CustomerFundDetailDO query = new CustomerFundDetailDO();
        query.setUserId(userCache.getUserId());
        query.setFundType(requestVO.getFundType());
        List<CustomerFundDetailDO> pageList = iCustomerFundDetailService.findAppPageList(query, requestVO.getStartRow(), requestVO.getPageSize(), beginTime, endTime);
        responseVO.setFundDetailList(pageList);
        LOGGER.info(LogUtils.appLog("查询用户资金流水，出参：{}"), responseVO);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}

