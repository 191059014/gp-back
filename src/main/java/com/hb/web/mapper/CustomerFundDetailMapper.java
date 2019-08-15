package com.hb.web.mapper;

import com.hb.entity.CustomerFundDetailDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerFundDetailMapper {

    int deleteByPrimaryKey(String detailId);

    int insertSelective(CustomerFundDetailDO record);

    CustomerFundDetailDO selectByPrimaryKey(String detailId);

    int updateByPrimaryKeySelective(CustomerFundDetailDO record);

    List<CustomerFundDetailDO> findListByCondition(@Param("customerFundDetailDO") CustomerFundDetailDO customerFundDetailDO, @Param("startRow") Integer startRow, @Param("pageSize") Integer pageSize);

    Integer findCountByCondition(@Param("customerFundDetailDO") CustomerFundDetailDO customerFundDetailDO);

    CustomerFundDetailDO findOne(@Param("customerFundDetailDO") CustomerFundDetailDO customerFundDetailDO);

}