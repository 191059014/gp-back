package com.hb.web.api;

import com.hb.facade.entity.RoleDO;
import com.hb.facade.vo.webvo.response.RoleTreeResponseVO;

import java.util.List;
import java.util.Set;

/**
 * ========== 角色 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IRoleService.java, v1.0
 * @date 2019年07月17日 09时38分
 */
public interface IRoleService {

    /**
     * ########## 查找角色集合 ##########
     *
     * @param roleDO   角色信息
     * @param pageNum  当前页数
     * @param pageSize 每页条数
     * @return 角色集合
     */
    List<RoleDO> findPageList(RoleDO roleDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 查询总条数 ##########
     *
     * @param roleDO 用户信息
     * @return 总条数
     */
    Integer findCount(RoleDO roleDO);

    /**
     * ########## 添加角色 ##########
     *
     * @param roleDO 角色信息
     * @return 是否成功
     */
    int addRole(RoleDO roleDO);

    /**
     * ########## 修改角色 ##########
     *
     * @param roleDO 角色信息
     * @return 是否成功
     */
    int updateByPrimaryKeySelective(RoleDO roleDO);

    /**
     * ########## 通过roleId删除用户 ##########
     *
     * @param roleId 角色ID
     * @return 是否成功
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * ########## 通过roleId获取权限值集合 ##########
     *
     * @param roleId 角色ID
     * @return 权限值集合
     */
    Set<String> getPermissionByRoleId(Integer roleId);

    /** 
     * ##########  ##########
     *
     * @return
     */
    List<RoleTreeResponseVO> findRoleTree();

}
