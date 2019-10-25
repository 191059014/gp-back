package com.hb.web.container;

import com.hb.unic.base.container.BaseServiceLocator;
import com.hb.unic.cache.service.impl.RedisCacheServiceImpl;
import com.hb.unic.util.util.EncryptUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ========== 项目启动之后 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.container.SpringRunner.java, v1.0
 * @date 2019年06月11日 19时18分
 */
@RestController
@RequestMapping("self/tools")
@Api(tags = "系统工具")
public class SelfRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelfRunner.class);

    @Autowired
    private RedisCacheServiceImpl redisCacheServiceImpl;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("========================");
        System.out.println(" server start complete");
        System.out.println(" you can enjoy yourself");
        System.out.println("========================");
    }

    @ApiOperation(value = "获取缓存")
    @GetMapping("/getProperty")
    public String getProperty(@RequestParam("key") String key) {
        return redisCacheServiceImpl.get(key);
    }

    @ApiOperation(value = "清除缓存")
    @GetMapping("/clearProperty")
    public Boolean clearProperty(@RequestParam("key") String key) {
        return redisCacheServiceImpl.delete(key);
    }

    @ApiOperation(value = "强制关掉应用")
    @GetMapping("/stopApplication")
    public void stopApplication() {
        ApplicationContext applicationContext = BaseServiceLocator.getApplicationContext();
        if (applicationContext instanceof ConfigurableApplicationContext) {
            System.out.println("stopApplication");
            ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
            context.close();
        } else {
            while (true) {
                try {
                    int i = 9 / 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @ApiOperation(value = "获取加密后的密码")
    @GetMapping("/getEncryptText")
    public String getEncryptText(@RequestParam("password") String password) {
        return EncryptUtils.encode(password);
    }

}
