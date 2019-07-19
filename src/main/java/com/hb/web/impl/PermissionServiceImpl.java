package com.hb.web.impl;

import com.hb.web.api.IPermissionService;
import com.hb.web.constant.enumutil.SourceTypeEnum;
import com.hb.web.mapper.PermissionMapper;
import com.hb.web.model.PermissionDO;
import com.hb.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * ========== 权限 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.impl.PermissionServiceImpl.java, v1.0
 * @date 2019年07月17日 11时16分
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDO> findPageList(PermissionDO permissionDO, Integer pageNum, Integer pageSize) {
        return permissionMapper.findPageList(permissionDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(PermissionDO permissionDO) {
        return permissionMapper.findCount(permissionDO);
    }

    @Override
    public List<PermissionDO> findList(PermissionDO permissionDO) {
        return permissionMapper.findList(permissionDO);
    }

    @Override
    public int addPermission(PermissionDO permissionDO) {
        return permissionMapper.insertSelective(permissionDO);
    }

    @Override
    public int updateByPrimaryKeySelective(PermissionDO permissionDO) {
        return permissionMapper.updateByPrimaryKeySelective(permissionDO);
    }

    @Override
    public int deleteByPrimaryKey(Integer permissionId) {
        return permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public List<Map<String, Object>> getSourceTypeList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", sourceTypeEnum.getValue());
            map.put("name", sourceTypeEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean batchInsert(List<PermissionDO> permissionList) {
        int deleteNum = permissionMapper.deleteAll();
        int insertNum = permissionMapper.batchInsert(permissionList);
        return true;
    }

    @Override
    public Set<String> getPermissionValueSetByPermissionIds(Set<Integer> permissionSet, Integer sourceType) {
        return permissionMapper.getPermissionValueSetByPermissionIds(permissionSet, sourceType);
    }

    @Override
    public Set<String> getAllPermissionValueSet() {
        return permissionMapper.getAllPermissionValueSet();
    }

}
