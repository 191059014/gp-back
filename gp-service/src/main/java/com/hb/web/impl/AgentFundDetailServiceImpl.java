package com.hb.web.impl;

import com.hb.web.mapper.AgentFundDetailMapper;
import com.hb.facade.entity.AgentFundDeailDO;
import com.hb.facade.enumutil.FundTypeEnum;
import com.hb.web.vo.webvo.request.AgentFundDetailRequestVO;
import com.hb.unic.util.helper.PageHelper;
import com.hb.web.api.IAgentFundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ========== 代理商资金流水service实现类 ==========
 *
 * @author Mr.huang
 * @version AgentFundDetailServiceImpl.java, v1.0
 * @date 2019年06月13日 15时30分
 */
@Service
public class AgentFundDetailServiceImpl implements IAgentFundDetailService {

    @Autowired
    private AgentFundDetailMapper agentFundDetailMapper;

    @Override
    public List<AgentFundDeailDO> findAgentFundDetailList(AgentFundDetailRequestVO agentFundDetailRequestVO, Integer pageNum, Integer pageSize) {
        return agentFundDetailMapper.findAgentFundDetailList(agentFundDetailRequestVO, PageHelper.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(AgentFundDetailRequestVO agentFundDetailRequestVO) {
        return agentFundDetailMapper.findCount(agentFundDetailRequestVO);
    }

    @Override
    public List<Map<String, Object>> getFundTypeList() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (FundTypeEnum fundTypeEnum : FundTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value", fundTypeEnum.getValue());
            map.put("name", fundTypeEnum.getName());
            resultList.add(map);
        }
        return resultList;
    }

}
