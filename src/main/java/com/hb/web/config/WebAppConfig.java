package com.hb.web.config;

import com.hb.web.interceptor.MainInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ========== 可以自定义一些web配置 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.config.WebAppConfig.java, v1.0
 * @date 2019年06月11日 16时00分
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MainInterceptor()).addPathPatterns("/**");
    }

}
