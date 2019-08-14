package com.hb.web.android.api.noauth;

import com.hb.web.android.base.BaseApp;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.tool.JsonFileParseTools;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.util.LogUtils;
import com.hb.web.vo.appvo.request.ResourceRequestVO;
import com.hb.web.vo.appvo.response.ResourceResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ========== 获取资源 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.android.api.noauth.ResourceApp.java, v1.0
 * @date 2019年08月14日 09时22分
 */
@Api(tags = "[APP]资源")
@RestController
@RequestMapping("app/noauth/resource")
public class ResourceApp extends BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceApp.class);

    /**
     * ########## 根据路径获取静态资源 ##########
     *
     * @param requestVO 请求参数
     * @return json
     */
    @ApiOperation(value = "根据路径获取静态资源")
    @PostMapping("/getResource")
    public AppResultModel<ResourceResponseVO> getResource(@RequestBody ResourceRequestVO requestVO) {
        LOGGER.info(LogUtils.appLog("根据路径获取静态资源，入参：{}"), requestVO);
        String json = JsonFileParseTools.readJsonFile2String(requestVO.getPath());
        LOGGER.info(LogUtils.appLog("根据路径获取静态资源，出参：{}"), json);
        ResourceResponseVO responseVO = new ResourceResponseVO(json);
        return AppResultModel.generateResponseData(AppResponseCodeEnum.SUCCESS, responseVO);
    }

}
