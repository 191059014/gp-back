package com.hb.web.api;

import com.hb.facade.entity.HotNewsDO;

import java.util.List;

public interface IHotNewsService {

    List<HotNewsDO> findHotNewsList(HotNewsDO hotNewsDO, Integer pageNum, Integer pageSize);

    Integer findCount(HotNewsDO hotNewsDO);

    int addHotNews(HotNewsDO hotNewsDO);

    int updateByPrimaryKeySelective(HotNewsDO hotNewsDO);

    List<HotNewsDO> findLastestHotNewsList(Integer startRow, Integer pageSize);

}
