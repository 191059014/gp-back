package com.hb.web.mapper;

import com.hb.web.model.CustomerFundDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerFundMapper {

    int deleteByPrimaryKey(String userId);

    int insertSelective(CustomerFundDO record);

    CustomerFundDO selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(CustomerFundDO record);

    List<CustomerFundDO> findCustomerFundList(@Param("customerFundDO") CustomerFundDO customerFundDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCount(@Param("customerFundDO") CustomerFundDO customerFundDO);

    CustomerFundDO findCustomerFund(@Param("customerFundDO") CustomerFundDO customerFundDO);

}