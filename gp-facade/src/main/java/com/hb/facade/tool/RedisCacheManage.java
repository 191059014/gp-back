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

}
