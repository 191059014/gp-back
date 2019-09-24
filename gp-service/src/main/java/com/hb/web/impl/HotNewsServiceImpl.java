package com.hb.web.impl;

import com.hb.facade.entity.HotNewsDO;
import com.hb.unic.util.helper.PageHelper;
import com.hb.web.api.IHotNewsService;
import com.hb.web.mapper.HotNewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotNewsServiceImpl implements IHotNewsService {

    @Autowired
    private HotNewsMapper hotNewsMapper;

    @Override
    public List<HotNewsDO> findHotNewsList(HotNewsDO hotNewsDO, Integer pageNum, Integer pageSize) {
        return hotNewsMapper.findHotNewsList(hotNewsDO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(HotNewsDO hotNewsDO) {
        return hotNewsMapper.findCount(hotNewsDO);
    }

    @Override
    public int addHotNews(HotNewsDO hotNewsDO) {
        return hotNewsMapper.insertSelective(hotNewsDO);
    }

    @Override
    public int updateByPrimaryKeySelective(HotNewsDO hotNewsDO) {
        return hotNewsMapper.insertSelective(hotNewsDO);
    }

    @Override
    public List<HotNewsDO> findLastestHotNewsList(Integer startRow, Integer pageSize) {
        return hotNewsMapper.findHotNewsList(new HotNewsDO(), startRow, pageSize);
    }

}
