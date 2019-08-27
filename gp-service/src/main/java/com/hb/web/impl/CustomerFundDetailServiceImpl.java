package com.hb.web.impl;

import com.hb.web.mapper.CustomerFundDetailMapper;
import com.hb.facade.entity.CustomerFundDetailDO;
import com.hb.unic.util.helper.PageHelper;
import com.hb.web.api.ICustomerFundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ========== 客户资金流水service实现类 ==========
 *
 * @author Mr.huang
 * @version CustomerFundDetailServiceImpl.java, v1.0
 * @date 2019年06月17日 14时32分
 */
@Service
public class CustomerFundDetailServiceImpl implements ICustomerFundDetailService {

    @Autowired
    private CustomerFundDetailMapper customerFundDetailMapper;

    @Override
    public List<CustomerFundDetailDO> findListByCondition(CustomerFundDetailDO customerFundDetailDO, Integer pageNum, Integer pageSize) {
        return customerFundDetailMapper.findListByCondition(customerFundDetailDO, PageHelper.getStartRow(pageNum, pageSize), pageSize, null, null);
    }

    @Override
    public List<CustomerFundDetailDO> findAppPageList(CustomerFundDetailDO customerFundDetailDO, Integer startRow, Integer pageSize, Date beginTime, Date endTime) {
        return customerFundDetailMapper.findListByCondition(customerFundDetailDO, startRow, pageSize, beginTime, endTime);
    }

    @Override
    public Integer findCountByCondition(CustomerFundDetailDO customerFundDetailDO) {
        return customerFundDetailMapper.findCountByCondition(customerFundDetailDO);
    }

    @Override
    public int addOne(CustomerFundDetailDO customerFundDetailDO) {
        return customerFundDetailMapper.insertSelective(customerFundDetailDO);
    }

    @Override
    public CustomerFundDetailDO findOne(CustomerFundDetailDO customerFundDetailDO) {
        return customerFundDetailMapper.findOne(customerFundDetailDO);
    }

    @Override
    public int updateByPrimaryKeySelective(CustomerFundDetailDO customerFundDetailDO) {
        return customerFundDetailMapper.updateByPrimaryKeySelective(customerFundDetailDO);
    }

    @Override
    public int deleteById(String detailId) {
        return customerFundDetailMapper.deleteByPrimaryKey(detailId);
    }

}
