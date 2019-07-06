package com.hb.web.android.api.auth;

import com.hb.web.api.IOfflinePayService;
import com.hb.web.model.AppResponseCodeEnum;
import com.hb.web.model.AppResultModel;
import com.hb.web.model.OfflinePayChekDO;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.appvo.request.QueryOfflinePayCheckRequestVO;
import com.hb.web.vo.appvo.response.OfflinePayQueryResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @ApiOperation(value = "查询线下支付审核信息")
    @PostMapping("/queryOfflinePayCheck")
    public AppResultModel<OfflinePayQueryResponseVO> queryOfflinePayCheck(@RequestBody QueryOfflinePayCheckRequestVO queryOfflinePayCheckRequestVO) {
        LOGGER.info(LogUtils.appLog("查询线下支付审核信息，入参：{}"), queryOfflinePayCheckRequestVO);
        OfflinePayQueryResponseVO responseVO = new OfflinePayQueryResponseVO();
        OfflinePayChekDO query = new OfflinePayChekDO();
        query.setCheckStatus(queryOfflinePayCheckRequestVO.getCheckStatus());
        query.setPayStatus(queryOfflinePayCheckRequestVO.getPayStatus());
        query.setPayChannel(queryOfflinePayCheckRequestVO.getPayChannel());
        List<OfflinePayChekDO> list = iOfflinePayService.findList(query, null, null);
        responseVO.setOfflinePayList(list);
        LOGGER.info(LogUtils.appLog("查询线下支付审核信息，返回结果：{}"), list);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}
