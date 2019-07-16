package com.hb.web.container;

import com.hb.web.tool.RedisTools;
import com.hb.web.util.YamlUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * ========== 项目启动之后 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.container.SpringRunner.java, v1.0
 * @date 2019年06月11日 19时18分
 */
@RestController
@RequestMapping("tools")
@Api(tags = "系统工具")
public class SelfRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelfRunner.class);

    @Autowired
    private RedisTools redisTools;

    @Override
    public void run(String... args) throws Exception {
        String path = "common/general-config.yml";
        loadYmlProperties(path);
    }

    @ApiOperation(value = "加载缓存")
    @RequestMapping("/loadYmlProperties")
    public String loadYmlProperties(String path) {
        LOGGER.info("load yml properties of path : {}", path);
        Properties yml = YamlUtils.readYml(path);
        for (Object key : yml.keySet()) {
            Object value = yml.get(key);
            LOGGER.info("key : {}, value : {}", key, value);
            redisTools.set((String) key, value);
        }
        return "SUCCESS";
    }

    @ApiOperation(value = "获取缓存")
    @RequestMapping("/getProperty")
    public String getProperty(String key) {
        if (redisTools.hasKey(key)) {
            return redisTools.get(key);
        }
        return "cannot found key in cache";
    }

    @ApiOperation(value = "清除缓存")
    @RequestMapping("/clearProperty")
    public Boolean clearProperty(String key) {
        if (StringUtils.isBlank(key)) {
            LOGGER.info("cannot found key in cache");
            return false;
        }
        return redisTools.delete(key);
    }

}
