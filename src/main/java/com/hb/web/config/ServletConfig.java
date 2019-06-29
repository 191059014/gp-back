package com.hb.web.config;

import com.hb.web.servlet.MainDispatcherServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.config.BeanConfig.java, v1.0
 * @date 2019年06月02日 01时11分
 */
@Configuration
public class ServletConfig {

    /**
     * ########## 自定义dispatcherServlet ##########
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean dispatcherServletRegistrationBean(WebApplicationContext webApplicationContext) {
        //通过构造函数指定dispatcherServlet的上下文
        MainDispatcherServlet mainDispatcherServlet = new MainDispatcherServlet(webApplicationContext);
        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(mainDispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/*");
        //指定name为myDispatcherServlet
        registrationBean.setName("mainDispatcherServlet");
        return registrationBean;
    }

}
