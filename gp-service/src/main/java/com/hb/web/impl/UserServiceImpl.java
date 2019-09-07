package com.hb.web.impl;

import com.hb.web.mapper.UserMapper;
import com.hb.facade.entity.UserDO;
import com.hb.facade.enumutil.RealAuthStatusEnum;
import com.hb.unic.util.helper.PageHelper;
import com.hb.unic.util.util.DateUtils;
import com.hb.unic.util.util.EncryptUtils;
import com.hb.web.api.IAgentService;
import com.hb.web.api.IRolePermissionService;
import com.hb.web.api.IUserService;
import com.hb.facade.constant.GeneralConst;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ========== 用户service实现 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.sys.impl.UserServiceImpl.java, v1.0
 * @date 2019年06月03日 12时00分
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IAgentService iAgentService;

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @Override
    public List<UserDO> findUserList(UserDO userDO, Integer pageNum, Integer pageSize) {
        return userMapper.findUserPageList(userDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(UserDO userDO) {
        return userMapper.findCount(userDO);
    }

    @Override
    public UserDO findUser(UserDO userDO) {
        return userMapper.findUser(userDO);
    }

    @Override
    public boolean addUser(UserDO userDO) {
        // 密码加密
        userDO.setPassword(EncryptUtils.encode(userDO.getPassword()));
        // 创建时间
        userDO.setCreateTime(DateUtils.getCurrentDate());
        // TODO
        userDO.setCreateUserId(null);
        // 修改时间
        userDO.setUpdateTime(DateUtils.getCurrentDate());
        // TODO
        userDO.setUpdateUserId(null);
        // 状态
        userDO.setRecordStatus(GeneralConst.RECORD_STATUS_Y);
        return userMapper.insertSelective(userDO);
    }

    @Override
    public boolean updateUserById(String userId, UserDO userDO) {
        userDO.setUpdateTime(DateUtils.getCurrentDate());
        return userMapper.updateUserById(userId, userDO);
    }

    @Override
    public boolean deleteUserById(String agentId) {
        return userMapper.deleteUserById(agentId);
    }

    @Override
    public List<Map<String, Object>> getRealAuthStatusList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (RealAuthStatusEnum realAuthStatusEnum : RealAuthStatusEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", realAuthStatusEnum.getValue());
            map.put("name", realAuthStatusEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public Map<String, UserDO> getUserMapByUserIdSet(Set<String> userIdSet) {
        if (CollectionUtils.isEmpty(userIdSet)) {
            return new HashedMap();
        }
        List<UserDO> userList = userMapper.getUserListByUserIdSet(userIdSet);
        if (CollectionUtils.isEmpty(userList)) {
            return new HashedMap();
        }
        Map<String, UserDO> userMap = userList.stream().collect(Collectors.toMap(UserDO::getUserId, u -> u, (k, v) -> k));
        return userMap;
    }

    @Override
    public Set<String> getUserPermissionList(String userId) {
        return null;
    }
}
