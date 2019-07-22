package com.hb.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * web程序入口
 */
@SpringBootApplication
@ImportResource({"classpath*:META-INF/applicationContext-web.xml"})
@ComponentScan("com.hb.web.config")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}