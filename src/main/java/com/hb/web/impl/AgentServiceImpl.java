package com.hb.web.impl;

import com.hb.web.api.IAgentService;
import com.hb.web.constant.GeneralConst;
import com.hb.web.constant.enumutil.AgentLevelEnum;
import com.hb.web.model.AgentDO;
import com.hb.web.mapper.AgentMapper;
import com.hb.web.util.DateUtils;
import com.hb.web.util.EncryptUtils;
import com.hb.web.util.IDCreateUtils;
import com.hb.web.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== 代理商service实现类 ==========
 *
 * @author Mr.huang
 * @version AgentServiceImpl.java, v1.0
 * @date 2019年06月16日 12时25分
 */
@Service
public class AgentServiceImpl implements IAgentService {

    @Autowired
    private AgentMapper agentMapper;

    @Value("${gpweb.agent.agentUnit}")
    private String agentUnit;

    @Override
    public List<Map<String, Object>> getAgentLevelList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (AgentLevelEnum agentLevelEnum : AgentLevelEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", agentLevelEnum.getValue());
            map.put("name", agentLevelEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<AgentDO> findAgentList(AgentDO agentDO, Integer pageNum, Integer pageSize) {
        return agentMapper.findAgentList(agentDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(AgentDO agentDO) {
        return agentMapper.findCount(agentDO);
    }

    @Override
    public Integer addAgent(AgentDO agentDO) {
        // 密码加密
        agentDO.setPassword(EncryptUtils.encode(agentDO.getPassword()));
        // 创建时间
        agentDO.setCreateTime(DateUtils.getCurrentDate());
        // TODO
        agentDO.setCreateUserId(null);
        // 修改时间
        agentDO.setUpdateTime(DateUtils.getCurrentDate());
        // TODO
        agentDO.setUpdateUserId(null);
        // 状态
        agentDO.setRecordStatus(GeneralConst.RECORD_STATUS_Y);
        // 代理商编制
        agentDO.setAgentUnit(agentUnit);
        return agentMapper.insertSelective(agentDO);
    }

    @Override
    public AgentDO findAgent(AgentDO agentDO) {
        return agentMapper.findAgent(agentDO);
    }

    @Override
    public Integer updateAgentById(String agentId, AgentDO agentDO) {
        if (StringUtils.isNotBlank(agentDO.getPassword())) {
            agentDO.setPassword(EncryptUtils.encode(agentDO.getPassword()));
        }
        agentDO.setUpdateTime(DateUtils.getCurrentDate());
        return agentMapper.updateAgentById(agentId, agentDO);
    }

    @Override
    public Integer deleteAgentById(String agentId) {
        return agentMapper.deleteAgentById(agentId);
    }

}
