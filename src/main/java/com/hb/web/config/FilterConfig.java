package com.hb.web.config;

import com.hb.web.filter.CrossFilter;
import com.hb.web.filter.LoginFilter;
import com.hb.web.filter.SecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ========== 过滤器配置 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.config.FilterConfig.java, v1.0
 * @date 2019年06月11日 16时26分
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean buildCrossFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setFilter(new CrossFilter());
        filterRegistrationBean.setName("crossFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean buildSecurityFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.setFilter(new SecurityFilter());
        filterRegistrationBean.setName("securityFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean buildLoginFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setOrder(3);
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setName("loginFilter");
        filterRegistrationBean.addUrlPatterns("/controller/*");
        return filterRegistrationBean;
    }


}
