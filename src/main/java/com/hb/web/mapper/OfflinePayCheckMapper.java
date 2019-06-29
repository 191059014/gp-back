package com.hb.web.mapper;

import com.hb.web.model.OfflinePayChekDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OfflinePayCheckMapper {

    int deleteByPrimaryKey(Integer checkId);

    int insertSelective(OfflinePayChekDO record);

    OfflinePayChekDO selectByPrimaryKey(Integer checkId);

    int updateByPrimaryKeySelective(OfflinePayChekDO record);

    List<OfflinePayChekDO> findList(@Param("offlinePayChekDO") OfflinePayChekDO offlinePayChekDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("offlinePayChekDO") OfflinePayChekDO offlinePayChekDO);

    OfflinePayChekDO findOne(OfflinePayChekDO offlinePayChekDO);
}