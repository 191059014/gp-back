package com.hb.web.container;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.web.container.SpringRunner.java, v1.0
 * @date 2019年06月11日 19时18分
 */
@Component
public class SpringRunner implements CommandLineRunner {

    public static final String AGENT_UNIT_KEY = "agentUnit";

    public static final String AGENT_UNIT_VALUE = "001";

    @Override
    public void run(String... args) throws Exception {
        System.out.println("spring runner execute");
    }

}
