package com.hb.web.sharding;

import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.Collection;

/**
 * ========== 订单表分表策略 ==========
 *
 * @author Mr.huang
 * @version com.hb.web.sharding.OrderShardingRule.java, v1.0
 * @date 2019年07月14日 01时44分
 */
public class OrderShardingRule implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        return null;
    }

}
