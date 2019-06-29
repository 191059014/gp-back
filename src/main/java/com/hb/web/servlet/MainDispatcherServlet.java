package com.hb.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========== 自定义dispatcher servlet ==========
 *
 * @author Mr.huang
 * @version com.hb.web.config.MainDispatcherServlet.java, v1.0
 * @date 2019年06月02日 00时46分
 */
public class MainDispatcherServlet extends DispatcherServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainDispatcherServlet.class);

    public MainDispatcherServlet(WebApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("main dispatcher servlet start");
        LOGGER.info("main dispatcher servlet end");
        super.doDispatch(request, response);
    }

}
