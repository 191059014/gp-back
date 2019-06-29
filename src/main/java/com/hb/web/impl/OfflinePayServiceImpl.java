package com.hb.web.impl;

import com.hb.web.api.IOfflinePayService;
import com.hb.web.constant.enumutil.OfflineCheckStatusEnum;
import com.hb.web.constant.enumutil.OfflinePayChannelEnum;
import com.hb.web.constant.enumutil.OfflinePayStatusEnum;
import com.hb.web.model.OfflinePayChekDO;
import com.hb.web.mapper.OfflinePayCheckMapper;
import com.hb.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== 线下支付 ==========
 *
 * @author Mr.huang
 * @version OfflinePayServiceImpl.java, v1.0
 * @date 2019年06月12日 15时30分
 */
@Service
public class OfflinePayServiceImpl implements IOfflinePayService {

    @Autowired
    private OfflinePayCheckMapper offlinePayCheckMapper;

    @Override
    public List<Map<String, Object>> getOfflineCheckStatusList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OfflineCheckStatusEnum offlineCheckStatusEnum : OfflineCheckStatusEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", offlineCheckStatusEnum.getValue());
            map.put("name", offlineCheckStatusEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getOfflinePayStatusCombobox() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OfflinePayStatusEnum offlinePayStatusEnum : OfflinePayStatusEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", offlinePayStatusEnum.getValue());
            map.put("name", offlinePayStatusEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<Map<String, Object>> getOfflinePayChannelList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (OfflinePayChannelEnum offlinePayChannelEnum : OfflinePayChannelEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", offlinePayChannelEnum.getValue());
            map.put("name", offlinePayChannelEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public List<OfflinePayChekDO> findList(OfflinePayChekDO offlinePayChekDO, Integer pageNum, Integer pageSize) {
        return offlinePayCheckMapper.findList(offlinePayChekDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.findCount(offlinePayChekDO);
    }

    @Override
    public int addOne(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.insertSelective(offlinePayChekDO);
    }

    @Override
    public OfflinePayChekDO findOne(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.findOne(offlinePayChekDO);
    }

    @Override
    public int updateByPrimaryKeySelective(OfflinePayChekDO offlinePayChekDO) {
        return offlinePayCheckMapper.updateByPrimaryKeySelective(offlinePayChekDO);
    }

    @Override
    public int deleteById(Integer checkId) {
        return offlinePayCheckMapper.deleteByPrimaryKey(checkId);
    }

}
