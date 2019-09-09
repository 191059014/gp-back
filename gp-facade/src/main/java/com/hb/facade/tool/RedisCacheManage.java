package com.hb.facade.tool;

import com.alibaba.fastjson.JSON;
import com.hb.facade.common.RedisKeyFactory;
import com.hb.facade.constant.GeneralConst;
import com.hb.facade.entity.AgentDO;
import com.hb.facade.entity.UserDO;
import com.hb.unic.cache.service.ICacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RedisCacheManage {

    @Autowired
    private ICacheService redisCacheService;

    /**
     * 将用户信息放到缓存
     *
     * @param userDO 用户信息
     */
    public void setUserCache(UserDO userDO) {
        redisCacheService.set(RedisKeyFactory.getUserCacheKey(userDO.getUserId()), userDO, GeneralConst.USER_INFO_EXPIRE_TIME);
    }

    /**
     * 从缓存里获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    public UserDO getUserCache(String userId) {
        String json = redisCacheService.get(RedisKeyFactory.getUserCacheKey(userId));
        if (StringUtils.isBlank(json)) {
            return null;
        }
        UserDO userDO = JSON.parseObject(json, UserDO.class);
        return userDO;
    }

    /**
     * 将代理商信息放到缓存
     *
     * @param agentDO 代理商信息
     */
    public void setAgentCache(AgentDO agentDO) {
        redisCacheService.set(RedisKeyFactory.getAgentCacheKey(agentDO.getMobile()), agentDO, GeneralConst.AGENT_INFO_EXPIRE_TIME);
    }

    /**
     * 从缓存里获取代理商信息
     *
     * @param mobile 代理商ID
     * @return 代理商信息
     */
    public AgentDO getAgentCache(String mobile) {
        String json = redisCacheService.get(RedisKeyFactory.getAgentCacheKey(mobile));
        if (StringUtils.isBlank(json)) {
            return null;
        }
        AgentDO agentDO = JSON.parseObject(json, AgentDO.class);
        return agentDO;
    }

    /**
     * 设置涨停或者跌停的股票缓存
     *
     * @param stockCode 股票代码
     */
    public void setUpOrLowerStopStockCache(String stockCode) {
        String s = redisCacheService.get(RedisKeyFactory.getUpOrLowStopStockCacheKey());
        Set<String> set = null;
        if (StringUtils.isBlank(s)) {
            set = new HashSet<>();
            set.add(stockCode);
        } else {
            set = JSON.parseObject(s, Set.class);
            set.add(stockCode);
        }
        redisCacheService.set(RedisKeyFactory.getUpOrLowStopStockCacheKey(), set, GeneralConst.UP_OR_LOW_STOP_STOCK_EXPIRE_TIME);
    }

    /**
     * 获取涨停或者跌停的股票缓存
     *
     * @return 股票代码集合
     */
    public Set<String> getUpOrLowerStopStockCache() {
        String s = redisCacheService.get(RedisKeyFactory.getUpOrLowStopStockCacheKey());
        if (StringUtils.isBlank(s)) {
            return new HashSet<>();
        }
        Set set = JSON.parseObject(s, Set.class);
        return set;
    }

}
