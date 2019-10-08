package com.hb.web.impl;

import com.hb.facade.common.AppResponseCodeEnum;
import com.hb.facade.common.AppResultModel;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.enumutil.RealAuthStatusEnum;
import com.hb.remote.constant.enumutil.BankCardAuthResEnum;
import com.hb.remote.constant.enumutil.IdCardAuthResEnum;
import com.hb.remote.model.BankCardAuthResult;
import com.hb.remote.model.IdCardAuthResult;
import com.hb.remote.service.IRealNameAuth;
import com.hb.web.exception.BusinessException;
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
import com.hb.facade.constant.GeneralConst;
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

    @Autowired
    private IRealNameAuth realNameAuth;

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
        agentDO.setUnit(unit);
        return agentMapper.findAgentList(agentDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(AgentDO agentDO) {
        agentDO.setUnit(unit);
        return agentMapper.findCount(agentDO);
    }

    @Override
    public Integer addAgent(AgentDO agentDO, AgentDO agentCache) {
        // 密码加密
        agentDO.setPassword(EncryptUtils.encode(agentDO.getPassword()));
        agentDO.setCreateUserId(agentCache.getAgentId());
        agentDO.setUpdateUserId(agentCache.getAgentId());
        // 状态
        agentDO.setRecordStatus(GeneralConst.RECORD_STATUS_Y);
        if (agentDO.getUnit() == null) {
            agentDO.setAgentLevel(AgentLevelEnum.FIRST.getValue());
        } else {
            agentDO.setAgentLevel(AgentLevelEnum.SECOND.getValue());
        }
        return agentMapper.insertSelective(agentDO);
    }

    @Override
    public AgentDO findAgent(AgentDO agentDO) {
        return agentMapper.findAgent(agentDO);
    }

    @Override
    public Integer updateAgentById(String agentId, AgentDO agentDO) {
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

    @Override
    public AgentDO getAgentByInviterMobile(String inviterMobile) {
        AgentDO query = new AgentDO();
        query.setMobile(inviterMobile);
        AgentDO agent = findAgent(query);
        if (agent == null) {
            agent = new AgentDO();
        }
        return agent;
    }

    @Override
    public void realNameAuth(String idCardNo, String realName, AgentDO agentDO) {
        IdCardAuthResult idCardAuthResult = realNameAuth.idCardAuth(idCardNo, realName);
        AgentDO update = new AgentDO();
        update.setIdCardNo(idCardNo);
        update.setRealName(realName);
        if (idCardAuthResult == null || idCardAuthResult.getResult() == null) {
            update.setRealAuthStatus(RealAuthStatusEnum.AUTH_NOT_PASS.getValue());
            agentMapper.updateAgentById(agentDO.getAgentId(), update);
            throw new BusinessException(ResponseEnum.REALNAME_AUTH_FAILED);
        }
        update.setRealAuthStatus(RealAuthStatusEnum.IS_AUTH.getValue());
        Integer updateResult = agentMapper.updateAgentById(agentDO.getAgentId(), update);
        if (updateResult <= 0) {
            throw new BusinessException(ResponseEnum.ERROR);
        }
    }

    @Override
    public void bindBankCard(String bankNo, String bankName, AgentDO agentDO) {
        AgentDO agent = agentMapper.findAgent(new AgentDO(agentDO.getAgentId()));
        AgentDO update = new AgentDO(agentDO.getAgentId());
        update.setBankNo(bankNo);
        update.setBankName(bankName);
        if (RealAuthStatusEnum.IS_AUTH.getValue().compareTo(agent.getRealAuthStatus()) != 0) {
            throw new BusinessException(ResponseEnum.PLEASE_REALNAME_AUTH);
        }
        BankCardAuthResult bankCardAuthResult = realNameAuth.bankCardAuth(bankNo, agent.getIdCardNo(), agent.getRealName());
        if (bankCardAuthResult == null || bankCardAuthResult.getResult() == null) {
            update.setBankRealAuthStatus(RealAuthStatusEnum.AUTH_NOT_PASS.getValue());
            agentMapper.updateAgentById(agentDO.getAgentId(), update);
            throw new BusinessException(ResponseEnum.BANKCARD_AUTH_FAILED);
        }
        if (!StringUtils.equals(BankCardAuthResEnum.success.getCode(), bankCardAuthResult.getCode())) {
            update.setBankRealAuthStatus(RealAuthStatusEnum.AUTH_NOT_PASS.getValue());
            agentMapper.updateAgentById(agentDO.getAgentId(), update);
            throw new BusinessException(ResponseEnum.BANKCARD_AUTH_FAILED);
        }
        update.setBankRealAuthStatus(RealAuthStatusEnum.IS_AUTH.getValue());
//        update.setBankName(bankCardAuthResult.getResult().getBank());
        Integer updateResult = agentMapper.updateAgentById(agentDO.getAgentId(), update);
        if (updateResult <= 0) {
            throw new BusinessException(ResponseEnum.ERROR);
        }
    }


}
