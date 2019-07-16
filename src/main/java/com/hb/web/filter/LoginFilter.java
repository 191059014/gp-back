package com.hb.web.filter;

import com.alibaba.fastjson.JSON;
import com.hb.web.constant.GeneralConst;
import com.hb.web.common.ResponseEnum;
import com.hb.web.container.SpringUtil;
import com.hb.web.common.ResponseData;
import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.tool.RedisTools;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * ========== 登陆 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.filter.LoginFilter.java, v1.0
 * @date 2019年06月11日 23时48分
 */
public class LoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

    private static boolean filterSwitch = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("login filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        LOGGER.info("requestURI:{}", requestURI);
        if (filterSwitch) {
            if (needLogin(requestURI)) {
                String authorization = request.getHeader("Authorization");
                if (StringUtils.isBlank(authorization)) {
                    ResponseData<Object> objectResponseData = ResponseData.generateResponseData(ResponseEnum.NO_SESSION);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(objectResponseData));
                    LOGGER.info("authorization from request is null, return");
                    return;
                }
                RedisTools redisTools = SpringUtil.getBean(RedisTools.class);
                String agentCacheStr = redisTools.get(GeneralConst.USER_SESSION_KEY + authorization);
                if (StringUtils.isBlank(agentCacheStr)) {
                    ResponseData<Object> objectResponseData = ResponseData.generateResponseData(ResponseEnum.NO_SESSION);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(objectResponseData));
                    LOGGER.info("authorization from cache is null, return");
                    return;
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * ########## 是否需要登陆 ##########
     *
     * @param url 请求地址
     * @return 是否需要登陆
     */
    private boolean needLogin(String url) {
        Set<String> greenSet = new HashSet<String>() {
            {
                add("/controller/login/login");
            }
        };
        if (!greenSet.contains(url)) {
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
        LOGGER.info("login filter destroy");
    }
}
