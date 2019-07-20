package com.hb.web.base;

import com.alibaba.fastjson.JSON;
import com.hb.web.common.ResponseData;
import com.hb.web.common.ResponseEnum;
import com.hb.web.constant.GeneralConst;
import com.hb.web.model.AgentDO;
import com.hb.web.tool.AlarmTools;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.tool.RedisTools;
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
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;

    @Autowired
    public RedisTools redisTools;

    @Autowired
    private AlarmTools alarmTools;

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
        String authorization = request.getHeader("Authorization");
        String agentCacheStr = redisTools.get(GeneralConst.USER_SESSION_KEY + authorization);
        AgentDO agentDO = JSON.parseObject(agentCacheStr, AgentDO.class);
        return agentDO;
    }

}
