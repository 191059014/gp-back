package com.hb.web.api;

import com.hb.facade.entity.OfflinePayChekDO;

import java.util.List;
import java.util.Map;

/**
 * ========== 线下支付 ==========
 *
 * @author Mr.huang
 * @version IOfflinePayService.java, v1.0
 * @date 2019年06月12日 15时29分
 */
public interface IOfflinePayService {

    /**
     * ########## 获取所有的审核状态 ##########
     *
     * @return 审核状态集合
     */
    List<Map<String, Object>> getOfflineCheckStatusList();

    /**
     * ########## 获取所有的支付渠道 ##########
     *
     * @return 支付渠道集合
     */
    List<Map<String, Object>> getOfflinePayChannelList();

    /**
     * ########## 分页查询线下支付列表 ##########
     *
     * @param pageNum          当前第几页
     * @param pageSize         每页条数
     * @param offlinePayChekDO 线下支付审核实体
     * @return 分页结果
     */
    List<OfflinePayChekDO> findList(OfflinePayChekDO offlinePayChekDO, Integer pageNum, Integer pageSize);

    /**
     * ########## 分页查询线下支付列表 ##########
     *
     * @param startRow         开始页数
     * @param pageSize         每页条数
     * @param offlinePayChekDO 线下支付审核实体
     * @return 分页结果
     */
    List<OfflinePayChekDO> findAppList(OfflinePayChekDO offlinePayChekDO, Integer startRow, Integer pageSize);

    /**
     * ########## 查询线下支付列表总条数 ##########
     *
     * @param offlinePayChekDO 线下支付审核实体
     * @return 总条数
     */
    Integer findCount(OfflinePayChekDO offlinePayChekDO);

    /**
     * ########## 添加审核信息 ##########
     *
     * @param offlinePayChekDO 审核信息
     * @return 成功条数
     */
    int addOne(OfflinePayChekDO offlinePayChekDO);

    /**
     * ########## 查询一条审核信息 ##########
     *
     * @param offlinePayChekDO 审核信息
     * @return 审核信息
     */
    OfflinePayChekDO findOne(OfflinePayChekDO offlinePayChekDO);

    /**
     * ########## 更新审核信息 ##########
     *
     * @param offlinePayChekDO 审核信息
     * @return 更新条数
     */
    int updateByPrimaryKeySelective(OfflinePayChekDO offlinePayChekDO);

    /**
     * ########## 删除审核信息 ##########
     *
     * @param checkId 审核ID
     * @return 更新条数
     */
    int deleteById(Integer checkId);

    /**
     * ########## 获取支付状态下拉框 ##########
     *
     * @return 支付状态集合
     */
    List<Map<String, Object>> getOfflinePayStatusCombobox();

    /**
     * ########## 线下支付审核 ##########
     *
     * @param offlinePayChekDO 线下支付审核信息
     */
    void update(OfflinePayChekDO offlinePayChekDO);

}
