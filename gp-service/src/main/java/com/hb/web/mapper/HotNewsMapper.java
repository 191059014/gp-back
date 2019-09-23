package com.hb.web.mapper;

import com.hb.facade.entity.HotNewsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotNewsMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(HotNewsDO record);

    HotNewsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HotNewsDO record);

    List<HotNewsDO> findHotNewsList(@Param("hotNewsDO") HotNewsDO hotNewsDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("hotNewsDO") HotNewsDO hotNewsDO);
}