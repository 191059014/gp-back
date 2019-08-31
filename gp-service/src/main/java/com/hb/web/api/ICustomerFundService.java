package com.hb.web.api;

import com.hb.facade.entity.CustomerFundDO;

import java.util.List;

/**
 * ========== 客户资金信息service接口 ==========
 *
 * @author Mr.huang
 * @version ICustomerFundService.java, v1.0
 * @date 2019年06月16日 21时13分
 */
public interface ICustomerFundService {

    /**
     * ########## 分页查找客户资金信息列表 ##########
     *
     * @param customerFundDO 客户资金信息
     * @param pageNum        当前页数
     * @param pageSize       每页显示条数
     * @return List<CustomerFundDO>
     */
    List<CustomerFundDO> findCustomerFundList(CustomerFundDO customerFundDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 查找客户资金信息总条数 ##########
     *
     * @param customerFundDO 客户资金信息
     * @return Integer
     */
    Integer findCount(CustomerFundDO customerFundDO);

    /**
     * ########## 添加客户资金信息 ##########
     *
     * @param customerFundDO 客户资金信息
     * @return int
     */
    int addCustomerFund(CustomerFundDO customerFundDO);

    /**
     * ########## 查找客户资金信息 ##########
     *
     * @param customerFundDO 客户资金信息
     * @return CustomerFundDO
     */
    CustomerFundDO findCustomerFund(CustomerFundDO customerFundDO);

    /**
     * ########## 更新客户资金信息 ##########
     *
     * @param customerFundDO 客户资金信息
     * @return int
     */
    int updateByPrimaryKeySelective(CustomerFundDO customerFundDO);

    /**
     * ########## 删除客户资金信息 ##########
     *
     * @param userId 客户ID
     * @return int
     */
    int deleteCustomerFundById(String userId);

    /**
     * 查询排行榜
     *
     * @param startRow 开始行
     * @param pageSize 每页条数
     * @return 用户资金信息列表
     */
    List<CustomerFundDO> getRankList(Integer startRow, Integer pageSize);
}
