package com.hb.web.base;

import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.entity.AgentDO;
import com.hb.remote.tool.AlarmTools;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.web.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========== web控制器超类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.base.BaseController.java, v1.0
 * @date 2019年06月06日 11时12分
 */
public class BaseController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private AlarmTools alarmTools;

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;

    /**
     * ########## 统一异常处理 ##########
     *
     * @param request   请求
     * @param response  响应
     * @param exception 异常
     * @return ResponseData
     */
    @ExceptionHandler
    public ResponseData exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("【web】统一异常处理 => {}", LogUtils.getStackTrace(exception));
        }
        alarmTools.alert("WEB", "统一异常处理", "系统异常", exception.getMessage());
        return ResponseData.generateResponseData(ResponseEnum.ERROR);
    }

    /**
     * ########## 获取代理商缓存信息 ##########
     *
     * @return 代理商
     */
    public AgentDO getAgentCache() {
        return CurrentSession.getAgentCache();
    }

}
