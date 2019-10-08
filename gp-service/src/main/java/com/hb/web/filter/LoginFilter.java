package com.hb.web.filter;

import com.alibaba.fastjson.JSON;
import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.constant.GeneralConst;
import com.hb.facade.entity.AgentDO;
import com.hb.facade.tool.RedisCacheManage;
import com.hb.unic.base.container.BaseServiceLocator;
import com.hb.unic.cache.service.ICacheService;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
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

    private static boolean filterSwitch = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("login filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
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
                ICacheService redisCacheService = (ICacheService) BaseServiceLocator.getBean("redisCacheService");
                String agentCacheStr = redisCacheService.get(GeneralConst.USER_SESSION_KEY + authorization);
                if (StringUtils.isBlank(agentCacheStr)) {
                    ResponseData<Object> objectResponseData = ResponseData.generateResponseData(ResponseEnum.NO_SESSION);
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(JSON.toJSONString(objectResponseData));
                    LOGGER.info("authorization from cache is null, return");
                    return;
                }
                // 追加用户过期时间
                AgentDO agentCache = JSON.parseObject(agentCacheStr, AgentDO.class);
                redisCacheService.set(GeneralConst.USER_SESSION_KEY + agentCache.getAgentId(), agentCache, GeneralConst.USER_SESSION_EXIRE_TIME);
                RedisCacheManage redisCacheManage = BaseServiceLocator.getBean(RedisCacheManage.class);
                redisCacheManage.setAgentCache(agentCache);
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
                add("/controller/hotNews/findLastestHotNewsList");
                add("/realNameAuth/testBankCardAuth");
                add("/realNameAuth/testIDCardAuth");
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
