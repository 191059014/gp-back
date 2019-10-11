package com.hb.web.base;

import com.alibaba.fastjson.JSON;
import com.hb.facade.constant.GeneralConst;
import com.hb.facade.entity.AgentDO;
import com.hb.unic.cache.service.impl.RedisCacheServiceImpl;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * ========== 当前session ==========
 *
 * @author Mr.huang
 * @version com.hb.web.base.CurrentSession.java, v1.0
 * @date 2019年10月11日 15时53分
 */
@Component
public class CurrentSession {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    public HttpServletRequest request;

    @Autowired
    public RedisCacheServiceImpl redisCacheServiceImpl;

    /**
     * ########## 获取代理商缓存信息 ##########
     *
     * @return 代理商
     */
    public AgentDO getAgentCache() {
        String authorization = request.getHeader("Authorization");
        String agentCacheStr = redisCacheServiceImpl.get(GeneralConst.USER_SESSION_KEY + authorization);
        AgentDO agentDO = JSON.parseObject(agentCacheStr, AgentDO.class);
        return agentDO;
    }

    /**
     * ########## 获取代理商编制 ##########
     *
     * @return 代理商
     */
    public Integer getAgentUnit() {
        AgentDO agentDO = getAgentCache();
        return agentDO.getUnit();
    }

}
