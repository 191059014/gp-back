package com.hb.web.api;

import com.hb.web.model.CustomerFundDetailDO;

import java.util.List;

/**
 * ========== 客户资金流水service接口 ==========
 *
 * @author Mr.huang
 * @version ICustomerFundDetailService.java, v1.0
 * @date 2019年06月17日 14时31分
 */
public interface ICustomerFundDetailService {

    /**
     * ########## 根据条件查询客户资金流水集合 ##########
     *
     * @param customerFundDetailDO 客户资金流水信息
     * @param pageNum              当前第几页
     * @param pageSize             每页条数
     * @return List<CustomerFundDetailDO>
     */
    List<CustomerFundDetailDO> findListByCondition(CustomerFundDetailDO customerFundDetailDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 分页条件查询客户资金流水集合 ##########
     *
     * @param customerFundDetailDO 客户资金流水信息
     * @param startRow             开始行数
     * @param pageSize             每页条数
     * @return List<CustomerFundDetailDO>
     */
    List<CustomerFundDetailDO> findAppPageList(CustomerFundDetailDO customerFundDetailDO, Integer startRow, Integer pageSize);

    /**
     * ########## 根据条件查询客户资金流水总条数 ##########
     *
     * @param customerFundDetailDO 客户资金流水信息
     * @return Integer
     */
    Integer findCountByCondition(CustomerFundDetailDO customerFundDetailDO);

    /**
     * ########## 新增一条客户资金流水 ##########
     *
     * @param customerFundDetailDO 客户资金流水信息
     * @return int
     */
    int addOne(CustomerFundDetailDO customerFundDetailDO);

    /**
     * ########## 查询一条客户资金流水 ##########
     *
     * @param customerFundDetailDO 客户资金流水信息
     * @return CustomerFundDetailDO
     */
    CustomerFundDetailDO findOne(CustomerFundDetailDO customerFundDetailDO);

    /**
     * ########## 根据主键更新一条客户资金流水 ##########
     *
     * @param customerFundDetailDO 客户资金流水信息
     * @return int
     */
    int updateByPrimaryKeySelective(CustomerFundDetailDO customerFundDetailDO);

    /**
     * ########## 根据主键删除一条客户资金流水 ##########
     *
     * @param detailId 客户资金流水ID
     * @return int
     */
    int deleteById(String detailId);

}
