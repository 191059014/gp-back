package com.hb.web.base;

import com.alibaba.fastjson.JSON;
import com.hb.facade.constant.GeneralConst;
import com.hb.facade.entity.AgentDO;
import com.hb.unic.base.container.BaseServiceLocator;
import com.hb.unic.cache.service.impl.RedisCacheServiceImpl;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ========== 当前session ==========
 *
 * @author Mr.huang
 * @version com.hb.web.base.CurrentSession.java, v1.0
 * @date 2019年10月11日 15时53分
 */
public class CurrentSession {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * ########## 获取代理商缓存信息 ##########
     *
     * @return 代理商
     */
    public static AgentDO getAgentCache() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RedisCacheServiceImpl redisCacheService = (RedisCacheServiceImpl) BaseServiceLocator.getBean("redisCacheService");
        String authorization = request.getHeader("Authorization");
        String agentCacheStr = redisCacheService.get(GeneralConst.USER_SESSION_KEY + authorization);
        AgentDO agentDO = JSON.parseObject(agentCacheStr, AgentDO.class);
        LOGGER.info("获取用户信息：{}", agentDO);
        return agentDO;
    }

    /**
     * ########## 获取代理商编制 ##########
     *
     * @return 代理商
     */
    public static Integer getAgentUnit() {
        AgentDO agentDO = getAgentCache();
        Integer unit = agentDO.getUnit();
        LOGGER.info("获取代理商编制：{}", unit);
        return unit;
    }

}
