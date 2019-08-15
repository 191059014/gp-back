package com.hb.web.impl;

import com.hb.web.api.ICustomerFundService;
import com.hb.entity.CustomerFundDO;
import com.hb.web.mapper.CustomerFundMapper;
import com.hb.web.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ========== 客户资金信息service实现类 ==========
 *
 * @author Mr.huang
 * @version CustomerFundServiceImpl.java, v1.0
 * @date 2019年06月16日 21时13分
 */
@Service
public class CustomerFundServiceImpl implements ICustomerFundService {

    @Autowired
    private CustomerFundMapper customerFundMapper;

    @Override
    public List<CustomerFundDO> findCustomerFundList(CustomerFundDO customerFundDO, Integer pageNum, Integer pageSize) {
        return customerFundMapper.findCustomerFundList(customerFundDO, PageUtils.getStartRow(pageNum, pageSize), pageSize);
    }

    @Override
    public Integer findCount(CustomerFundDO customerFundDO) {
        return customerFundMapper.findCount(customerFundDO);
    }

    @Override
    public int addCustomerFund(CustomerFundDO customerFundDO) {
        return customerFundMapper.insertSelective(customerFundDO);
    }

    @Override
    public CustomerFundDO findCustomerFund(CustomerFundDO customerFundDO) {
        return customerFundMapper.findCustomerFund(customerFundDO);
    }

    @Override
    public int updateByPrimaryKeySelective(CustomerFundDO customerFundDO) {
        return customerFundMapper.updateByPrimaryKeySelective(customerFundDO);
    }

    @Override
    public int deleteCustomerFundById(String userId) {
        return customerFundMapper.deleteByPrimaryKey(userId);
    }

}
