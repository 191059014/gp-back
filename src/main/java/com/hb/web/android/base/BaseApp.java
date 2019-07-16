package com.hb.web.android.base;

import com.hb.web.constant.AppConstant;
import com.hb.web.model.UserDO;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.util.LogUtils;
import com.hb.web.tool.RedisTools;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.tool.TokenTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * ========== App控制器超类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.base.BaseApp.java, v1.0
 * @date 2019年06月13日 23时42分
 */
public class BaseApp {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseApp.class);

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public HttpServletResponse response;

    @Autowired
    public RedisTools redisTools;

    /**
     * ########## 处理request里面的参数 ##########
     *
     * @return 参数集合
     */
    public Map<String, String> getParametersFromRequest() {
        Map<String, String> param = new HashMap<>(16);
        String varName;
        String varValue;
        Enumeration var = request.getParameterNames();
        // 循环获取参数
        while (var.hasMoreElements()) {
            Object obj = var.nextElement();
            varName = obj.toString();
            varValue = request.getParameter(varName);
            param.put(varName, varValue);
        }
        return param;
    }

    /**
     * ########## 统一异常处理 ##########
     *
     * @param exception 异常
     * @return AppResultModel
     */
    @ExceptionHandler
    public AppResultModel exceptionHandler(Exception exception) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(LogUtils.appLog("统一异常处理 => {}"), LogUtils.getStackTrace(exception));
        }
        return AppResultModel.generateResponseData(AppResponseCodeEnum.FAIL);
    }

    /**
     * ########## 获取用户token ##########
     *
     * @return token
     */
    public String getToken() {
        String token = request.getHeader(AppConstant.TOKEN);
        LOGGER.info(LogUtils.appLog("获取用户信息，token：{}"), token);
        return token;
    }

    /**
     * ########## 获取缓存中的用户信息 ##########
     *
     * @return 用户信息
     */
    public UserDO getUserCache() {
        String token = getToken();
        UserDO userCache = TokenTools.get(token, redisTools);
        LOGGER.info(LogUtils.appLog("获取用户缓存信息，结果：{}"), userCache.toString());
        return userCache;
    }


}
