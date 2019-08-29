package com.hb.web.android.api.auth;

import com.hb.facade.entity.OfflinePayChekDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.api.IOfflinePayService;
import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.web.util.LogUtils;
import com.hb.facade.vo.appvo.request.QueryOfflinePayCheckRequestVO;
import com.hb.facade.vo.appvo.response.OfflinePayQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ========== 线下支付审核 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.auth.OfflinePayCheckApp.java, v1.0
 * @date 2019年06月26日 22时20分
 */
@Api(tags = "[APP]线下支付审核")
@RestController
@RequestMapping("app/auth/offline")
public class OfflinePayCheckApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OfflinePayCheckApp.class);

    @Autowired
    private IOfflinePayService iOfflinePayService;

    @ApiOperation(value = "查询充值/提现记录")
    @PostMapping("/queryOfflinePayCheck")
    public AppResultModel<OfflinePayQueryResponseVO> queryOfflinePayCheck(@RequestBody QueryOfflinePayCheckRequestVO queryCondition) {
        LOGGER.info(LogUtils.appLog("查询线下支付审核信息，入参：{}"), queryCondition);
        OfflinePayQueryResponseVO responseVO = new OfflinePayQueryResponseVO();
        OfflinePayChekDO query = new OfflinePayChekDO();
        query.setFundType(queryCondition.getFundType());
        List<OfflinePayChekDO> list = iOfflinePayService.findAppList(query, queryCondition.getStartRow(), queryCondition.getPageSize());
        responseVO.setOfflinePayList(list);
        LOGGER.info(LogUtils.appLog("查询线下支付审核信息，返回结果：{}"), list);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}
