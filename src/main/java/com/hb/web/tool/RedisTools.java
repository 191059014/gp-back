package com.hb.web.tool;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ========== redis工具类 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.tool.RedisTools.java, v1.0
 * @date 2019年06月11日 00时19分
 */
@Component
public class RedisTools {

    /**
     * slf4j log
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTools.class);

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * ########## 放入缓存 ##########
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        String valueStr = JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, valueStr);
    }

    /**
     * ########## 放入缓存 ##########
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间
     */
    public void set(String key, Object value, long expireTime) {
        String valueStr = JSON.toJSONString(value);
        stringRedisTemplate.opsForValue().set(key, valueStr, expireTime, TIME_UNIT);
    }

    /**
     * ########## 从缓存获取 ##########
     *
     * @param key 键
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * ########## 删除缓存 ##########
     *
     * @param key 键
     */
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * ########## 判断缓存是否存在 ##########
     *
     * @param key 键
     */
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * ########## 根据key获取过期时间 ##########
     *
     * @param key 键
     */
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key, TIME_UNIT);
    }

    /**
     * ########## 根据key获取下一个值 ##########
     *
     * @param key 键
     */
    public Long getNextValue(String key) {
        return stringRedisTemplate.opsForValue().increment(key);
    }

}