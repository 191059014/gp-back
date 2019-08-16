package com.hb.web.filter;

import com.alibaba.fastjson.JSON;
import com.hb.facade.entity.UserDO;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.common.AppResponseCodeEnum;
import com.hb.web.common.AppResultModel;
import com.hb.web.constant.AppConstant;
import com.hb.web.container.BodyReaderHttpServletRequestWrapper;
import com.hb.web.container.SpringUtil;
import com.hb.web.tool.RedisTools;
import com.hb.web.tool.TokenTools;
import com.hb.web.util.LogUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ========== app端登陆过滤器 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.app.filter.AppLoginFilter.java, v1.0
 * @date 2019年06月13日 23时28分
 */
public class AppLoginFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppLoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("app login filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info(LogUtils.appLog("app login filter start"));
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUrl = request.getRequestURL().toString();
        HttpServletRequestWrapper wrapper = null;
        // 排除登陆操作，其他的要经过登录认证
        String token = request.getHeader(AppConstant.TOKEN);
        UserDO userDO = TokenTools.get(token, SpringUtil.getBean(RedisTools.class));
        if (userDO == null) {
            // token失效
            LOGGER.info(LogUtils.appLog("token[{}] is expired"), token);
            AppResultModel<Object> appResultModel = AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_TOKEN);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(appResultModel));
            LOGGER.info(LogUtils.appLog("app login filter end"));
            return;
        }
        String secret = request.getHeader(AppConstant.SECRET);
        String signature = request.getHeader(AppConstant.SIGNATURE);
        wrapper = new BodyReaderHttpServletRequestWrapper(request);
        String bodyString = ((BodyReaderHttpServletRequestWrapper) wrapper).getBody();
        String message = new StringBuilder().append(requestUrl).append(bodyString).append(secret).toString();
        String encode = EncryptUtils.encode(message);
        if (StringUtils.equals(signature, encode)) {
            // 签名错误
            LOGGER.info(LogUtils.appLog("signature is error, source is {}, target is {}"), signature, encode);
            AppResultModel<Object> appResultModel = AppResultModel.generateResponseData(AppResponseCodeEnum.ERROR_SIGNATURE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(appResultModel));
            LOGGER.info(LogUtils.appLog("app login filter end"));
            return;
        }
        LOGGER.info(LogUtils.appLog("app login filter end"));
        filterChain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
        LOGGER.info(LogUtils.appLog("app login filter destroy"));
    }

}
