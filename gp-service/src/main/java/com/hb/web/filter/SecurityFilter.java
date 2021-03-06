package com.hb.web.filter;

import com.alibaba.fastjson.JSON;
import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.constant.GeneralConst;
import com.hb.unic.base.BaseContext;
import com.hb.unic.base.container.BaseServiceLocator;
import com.hb.unic.cache.service.ICacheService;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ========== 安全过滤器 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.filter.SecurityFilter.java, v1.0
 * @date 2019年07月14日 10时32分
 */
public class SecurityFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("security filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ICacheService redisCacheService = (ICacheService) BaseServiceLocator.getBean("redisCacheService");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String blacklist = BaseContext.getValue("ip.blacklist");
        String userRealIpAddress = getUserIpAddress(request);
        LOGGER.info("the real ip address of request: {}", userRealIpAddress);
        if (blacklist != null && blacklist.contains(userRealIpAddress)) {
            LOGGER.warn("current ip is in blacklist, ip: {}", userRealIpAddress);
            ResponseData<Object> responseData = ResponseData.generateResponseData(ResponseEnum.REQUEST_IN_BLACKLIST);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(responseData));
            return;
        }
        String requestURL = request.getRequestURL().toString();
        if (!requestURL.contains("/app")) {
            String cacheKey = GeneralConst.REQUEST_IP_KEY + userRealIpAddress;
            String currentTimesStr = redisCacheService.get(cacheKey);
            int currentTimes = StringUtils.isBlank(currentTimesStr) ? 0 : Integer.parseInt(currentTimesStr);
            int timeBetween = Integer.parseInt(BaseContext.getValue("request.timeBetween"));
            int maxTimes = Integer.parseInt(BaseContext.getValue("request.maxTimes"));
            if (currentTimes > maxTimes) {
                LOGGER.warn("current request is too often, ip: {}", userRealIpAddress);
                ResponseData<Object> responseData = ResponseData.generateResponseData(ResponseEnum.REQUEST_TOO_OFTEN);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSON.toJSONString(responseData));
                return;
            }
            redisCacheService.set(cacheKey, ++currentTimes, timeBetween);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        LOGGER.info("security filter destroy");
    }

    /**
     * ########## 获取用户真实IP地址 ##########
     *
     * @param request 请求
     * @return ip地址
     */
    public static String getUserIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
