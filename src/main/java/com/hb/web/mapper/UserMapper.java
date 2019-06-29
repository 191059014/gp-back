package com.hb.web.mapper;

import com.hb.web.model.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * ========== 用户mapper ==========
 *
 * @author Mr.huang
 * @version com.hb.web.mapper.UserMapper.java, v1.0
 * @date 2019年06月03日 20时15分
 */
public interface UserMapper {

    /**
     * ########## 查找用户集合 ##########
     *
     * @param userDO   用户信息
     * @param startRow 起始行数
     * @param pageSize 每页条数
     * @return 用户集合
     */
    List<UserDO> findUserPageList(@Param("userDO") UserDO userDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    /**
     * ########## 查询总条数 ##########
     *
     * @param userDO   用户信息
     * @return 总条数
     */
    Integer findCount(@Param("userDO") UserDO userDO);

    /**
     * ########## 查询用户 ##########
     *
     * @param userDO   用户信息
     * @return 用户信息
     */
    UserDO findUser(@Param("userDO") UserDO userDO);

    /**
     * ########## 添加用户 ##########
     *
     * @param userDO 用户信息
     * @return 是否成功
     */
    boolean insertSelective(UserDO userDO);

    /**
     * ########## 修改用户 ##########
     *
     * @param userId 用户ID
     * @param userDO 用户信息
     * @return 是否成功
     */
    boolean updateUserById(@Param("userId") String userId, @Param("userDO") UserDO userDO);

    /**
     * ########## 通过userId删除用户 ##########
     *
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean deleteUserById(String userId);

    /**
     * ########## 通过用户ID集合查询用户信息集合 ##########
     *
     * @param userIdSet 用户ID集合
     * @return 用户信息集合
     */
    List<UserDO> getUserListByUserIdSet(@Param("userIdSet") Set<String> userIdSet);
}
