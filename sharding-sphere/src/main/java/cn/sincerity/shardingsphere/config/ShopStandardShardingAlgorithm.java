package cn.sincerity.shardingsphere.config;

import cn.sincerity.shardingsphere.domain.entity.Shoping;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Properties;

/**
 * ShopStandardShardingAlgorithm
 *
 * @author Ht7_Sincerity
 * @date 2023/7/28
 */
@Slf4j
public class ShopStandardShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    // 实现精确查询的方法（in、=查询会调用方法）
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        String logicTableName = shardingValue.getLogicTableName();
        Long id = shardingValue.getValue();
        BigInteger target = BigInteger.valueOf(id).mod(BigInteger.valueOf(2));
        String actualTableName = logicTableName + "_0" + target;
        if (availableTargetNames.contains(actualTableName)) {
            return actualTableName;
        }
        throw new UnsupportedOperationException("无法路由到目标表: " + actualTableName);
    }

    // 实现范围查询的方法（BETWEEN AND、>、<、>=、<=会调用的方法）
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        return availableTargetNames;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties props) {
        log.info("ShopStandardShardingAlgorithm init");
    }
}
