package com.hb.web.base;

import com.hb.web.model.ResponseEnum;
import com.hb.web.model.ResponseData;
import com.hb.web.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            LOGGER.info("【web】统一异常处理 => {}", LogUtils.getStackTrace(exception));
        }
        return ResponseData.generateResponseData(ResponseEnum.ERROR);
    }

}
