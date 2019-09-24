package com.hb.web.controller;

import com.hb.facade.common.ResponseData;
import com.hb.facade.common.ResponseEnum;
import com.hb.facade.entity.HotNewsDO;
import com.hb.web.api.IHotNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ========== 热点资讯controller ==========
 *
 * @author Mr.huang
 * @version com.hb.web.component.HotNewsController.java, v1.0
 * @date 2019年09月16日 21时10分
 */
@Api(tags = "[WEB]热点资讯")
@RestController
@RequestMapping("controller/hotNews")
public class HotNewsController {

    @Autowired
    private IHotNewsService iHotNewsService;

    @ApiOperation(value = "分页条件查询热点资讯列表")
    @PostMapping("/getHotNewsListPage")
    public ResponseData<List<HotNewsDO>> getHotNewsListPage(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestBody HotNewsDO hotNewsDO) {
        List<HotNewsDO> hotNewsList = iHotNewsService.findHotNewsList(hotNewsDO, pageNum, pageSize);
        Integer count = iHotNewsService.findCount(hotNewsDO);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, hotNewsList, count);
    }

    @ApiOperation(value = "添加热点资讯信息")
    @PostMapping("/addHotNews")
    public ResponseData addHotNews(@RequestBody HotNewsDO hotNewsDO) {
        int result = iHotNewsService.addHotNews(hotNewsDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "修改热点资讯信息")
    @PostMapping("/updateHotNewsById")
    public ResponseData updateHotNewsById(@RequestBody HotNewsDO hotNewsDO) {
        int result = iHotNewsService.updateByPrimaryKeySelective(hotNewsDO);
        if (result > 0) {
            return ResponseData.generateResponseData(ResponseEnum.SUCCESS);
        } else {
            return ResponseData.generateResponseData(ResponseEnum.ERROR);
        }
    }

    @ApiOperation(value = "查询最新的热点资讯")
    @PostMapping("/findLastestHotNewsList")
    public ResponseData<List<HotNewsDO>> findLastestHotNewsList() {
        List<HotNewsDO> hotNewsList = iHotNewsService.findLastestHotNewsList(1, 10);
        return ResponseData.generateResponseData(ResponseEnum.SUCCESS, hotNewsList);
    }

}
