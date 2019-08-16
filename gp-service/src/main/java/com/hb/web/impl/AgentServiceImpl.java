package com.hb.web.impl;

import com.hb.web.mapper.AgentMapper;
import com.hb.facade.entity.AgentDO;
import com.hb.facade.enumutil.AgentLevelEnum;
import com.hb.unic.logger.Logger;
import com.hb.unic.logger.LoggerFactory;
import com.hb.unic.util.helper.PageHelper;
import com.hb.unic.util.util.DateUtils;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.api.IAgentRoleService;
import com.hb.web.api.IAgentService;
import com.hb.web.api.IPermissionService;
import com.hb.web.api.IRolePermissionService;
import com.hb.web.constant.GeneralConst;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ========== 代理商service实现类 ==========
 *
 * @author Mr.huang
 * @version AgentServiceImpl.java, v1.0
 * @date 2019年06月16日 12时25分
 */
@Service
public class AgentServiceImpl implements IAgentService {

    private Logger LOGGER = LoggerFactory.getLogger(AgentServiceImpl.class);

    @Autowired
    private AgentMapper agentMapper;

    @Value("${gpweb.unit}")
    private Integer unit;

    @Autowired
    private IAgentRoleService iAgentRoleService;

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @Autowired
    private IPermissionService iPermissionService;

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
        return agentMapper.findAgentList(agentDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(AgentDO agentDO) {
        return agentMapper.findCount(agentDO);
    }

    @Override
    public Integer addAgent(AgentDO agentDO) {
        // 密码加密
        agentDO.setPassword(EncryptUtils.encode(agentDO.getPassword()));
        // TODO
        agentDO.setCreateUserId(null);
        // TODO
        agentDO.setUpdateUserId(null);
        // 状态
        agentDO.setRecordStatus(GeneralConst.RECORD_STATUS_Y);
        if (unit == null) {
            agentDO.setAgentLevel(AgentLevelEnum.FIRST.getValue());
        } else {
            agentDO.setAgentLevel(AgentLevelEnum.SECOND.getValue());
        }
        agentDO.setUnit(unit);

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

    @Override
    public Set<String> getPermissionSet(String agentId) {
        Set<String> permissionSet = null;
        if (StringUtils.equals("1", agentId)) {
            permissionSet = iPermissionService.getAllPermissionValueSet();
        } else {
            Set<Integer> roleIdSet = iAgentRoleService.getRoleIdSetByAgentId(agentId);
            if (CollectionUtils.isEmpty(roleIdSet)) {
                LOGGER.info("代理商[{}]，没有查询到角色", agentId);
                return null;
            }
            Set<Integer> permissionIdSet = iRolePermissionService.getPermissionIdSetByRoleIdSet(roleIdSet);
            if (CollectionUtils.isEmpty(permissionIdSet)) {
                LOGGER.info("角色[{}]，没有查询到权限", roleIdSet);
                return null;
            }
            permissionSet = iPermissionService.getPermissionValueSetByPermissionIds(permissionIdSet, null);
        }
        return permissionSet;
    }


}
