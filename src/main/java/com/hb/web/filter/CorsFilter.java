package com.hb.web.filter;

import com.hb.web.tool.Logger;
import com.hb.web.tool.LoggerFactory;
import com.hb.web.util.TraceIdUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * ========== 跨域 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.filter.CenterFilter.java, v1.0
 * @date 2019年06月02日 12时09分
 */
public class CorsFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("cors filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            TraceIdUtils.setTraceId(UUID.randomUUID().toString());
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String originUrl = request.getHeader("origin");
            String requestURL = request.getRequestURL().toString();
            LOGGER.info("cors filter src: {}，target: {}", originUrl, requestURL);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TraceIdUtils.removeTraceId();
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("cors filter destroy");
    }

}
