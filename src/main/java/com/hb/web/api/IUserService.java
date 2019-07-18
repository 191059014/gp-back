package com.hb.web.api;


import com.hb.web.model.UserDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 用户service接口 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.sys.IUserService.java, v1.0
 * @date 2019年06月03日 12时00分
 */
public interface IUserService {

    /**
     * ########## 查找用户集合 ##########
     *
     * @param userDO   用户信息
     * @param pageNum  当前页数
     * @param pageSize 每页条数
     * @return 用户集合
     */
    List<UserDO> findUserList(UserDO userDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 查询总条数 ##########
     *
     * @param userDO 用户信息
     * @return 总条数
     */
    Integer findCount(UserDO userDO);

    /**
     * ########## 查询用户 ##########
     *
     * @param userDO 用户信息
     * @return 用户信息
     */
    UserDO findUser(UserDO userDO);

    /**
     * ########## 添加用户 ##########
     *
     *
     * @param userDO 用户信息
     * @return 是否成功
     */
    boolean addUser(UserDO userDO);

    /**
     * ########## 修改用户 ##########
     *
     *
     * @param userId 用户ID
     * @param userDO 用户信息
     * @return 是否成功
     */
    boolean updateUserById(String userId, UserDO userDO);

    /**
     * ########## 通过agentId删除用户 ##########
     *
     * @param agentId 用户ID
     * @return 是否成功
     */
    boolean deleteUserById(String agentId);

    /**
     * ########## 获取所有实名认证状态 ##########
     *
     * @return 实名认证状态集合
     */
    List<Map<String, Object>> getRealAuthStatusList();

    /** 
     * ########## 通过用户ID集合查询用户信息集合 ##########
     *
     * @param userIdSet 用户ID集合
     * @return 用户信息集合
     */
    List<UserDO> getUserListByUserIdSet(Set<String> userIdSet);

    Set<String> getUserPermissionList(String userId);
}
