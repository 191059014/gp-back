package com.hb.web.api;

import com.hb.web.model.PermissionDO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ========== 权限 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.api.IPermissionService.java, v1.0
 * @date 2019年07月17日 09时39分
 */
public interface IPermissionService {

    /**
     * ########## 查找权限集合 ##########
     *
     * @param permissionDO 权限信息
     * @param pageNum      当前页数
     * @param pageSize     每页条数
     * @return 权限集合
     */
    List<PermissionDO> findPageList(PermissionDO permissionDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 查询总条数 ##########
     *
     * @param permissionDO 权限信息
     * @return 总条数
     */
    Integer findCount(PermissionDO permissionDO);

    List<PermissionDO> findList(PermissionDO permissionDO);

    /**
     * ########## 添加权限 ##########
     *
     * @param permissionDO 权限信息
     * @return 是否成功
     */
    int addPermission(PermissionDO permissionDO);

    /**
     * ########## 修改权限 ##########
     *
     * @param permissionDO 权限信息
     * @return 是否成功
     */
    int updateByPrimaryKeySelective(PermissionDO permissionDO);

    /**
     * ########## 通过Id删除权限 ##########
     *
     * @param permissionId 权限ID
     * @return 是否成功
     */
    int deleteByPrimaryKey(Integer permissionId);

    /**
     * ########## 获取资源类型集合 ##########
     *
     * @return 资源类型集合
     */
    List<Map<String, Object>> getSourceTypeList();

    /**
     * ########## 批量新增权限 ##########
     *
     * @param permissionList 权限几何信息
     * @return 是否成功
     */
    boolean batchInsert(List<PermissionDO> permissionList);

    /**
     * ########## 根据权限ID集合查询权限集合 ##########
     *
     * @param permissionSet 权限ID集合
     * @param sourceType
     * @return 权限值集合
     */
    Set<String> getPermissionValueSetByPermissionIds(Set<Integer> permissionSet, Integer sourceType);

    /**
     * ########## 查询所有权限 ##########
     *
     * @return 权限集合
     */
    Set<String> getAllPermissionValueSet();
}
