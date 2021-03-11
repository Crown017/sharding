package com.crown.sharding.config;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luhaihui
 * @create 2021/3/11 下午5:49
 */

@Configuration
public class KeyConfig {

    @Bean("userKeyGenerator")
    public SnowflakeShardingKeyGenerator userKeyGenerator() {
        return new SnowflakeShardingKeyGenerator();
    }

    @Bean("orderKeyGenerator")
    public SnowflakeShardingKeyGenerator orderKeyGenerator() {
        return new SnowflakeShardingKeyGenerator();
    }

}
