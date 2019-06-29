package com.hb.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========== 中心拦截器 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.interceptor.MainInterceptor.java, v1.0
 * @date 2019年06月02日 11时51分
 */
public class MainInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainInterceptor.class);

    /**
     * ########## 进入controller层之前 ##########
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("center interceptor preHandle");
        return true;
    }

    /**
     * ########## 处理请求完成后视图渲染之前 ##########
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("center interceptor postHandle");
    }

    /**
     * ########## 视图渲染之后 ##########
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("center interceptor afterCompletion");
    }

}
