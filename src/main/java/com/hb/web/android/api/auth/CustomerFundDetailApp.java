package com.hb.web.android.api.auth;

import com.hb.web.android.base.BaseApp;
import com.hb.web.api.ICustomerFundDetailService;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.model.CustomerFundDetailDO;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.appvo.request.QueryCustomerFundDetailRequestVO;
import com.hb.web.vo.appvo.request.QueryCustomerFundDetailResponseVO;
import com.hb.web.vo.appvo.request.QueryOrderRequestVO;
import com.hb.web.vo.appvo.response.OrderQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        if (requestVO.getFundType() == null || requestVO.getStartRow() == null || requestVO.getPageNum() == null) {
            return AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_PARAM_VERIFY);
        }
        CustomerFundDetailDO query = new CustomerFundDetailDO();
        query.setFundType(requestVO.getFundType());
        List<CustomerFundDetailDO> pageList = iCustomerFundDetailService.findAppPageList(query, requestVO.getStartRow(), requestVO.getPageNum());
        responseVO.setFundDetailList(pageList);
        LOGGER.info(LogUtils.appLog("查询用户资金流水，出参：{}"), responseVO);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}

