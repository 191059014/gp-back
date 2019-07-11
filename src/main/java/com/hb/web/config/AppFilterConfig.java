package com.hb.web.config;

import com.hb.web.filter.AppLoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ========== app端过滤器配置 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.config.FilterConfig.java, v1.0
 * @date 2019年06月11日 16时26分
 */
//@Configuration
public class AppFilterConfig {

    @Bean
    public FilterRegistrationBean buildAppLoginFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new AppLoginFilter());
        filterRegistrationBean.setName("appLoginFilter");
        filterRegistrationBean.addUrlPatterns("/app/auth/*");
        return filterRegistrationBean;
    }

}
