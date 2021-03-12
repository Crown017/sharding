package com.crown.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.crown.sharding.algorithm.HintOrderAlgorithm;
import com.crown.sharding.entity.UserShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.core.rule.TableRule;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.*;

@Configuration
public class DataSourceConfig {
    @Value("${datasource0.url}")
    private String url0;
    @Value("${datasource0.username}")
    private String username0;
    @Value("${datasource0.password}")
    private String password0;
    @Value("${datasource0.driver-class-name}")
    private String driverClassName0;

    @Value("${datasource1.url}")
    private String url1;
    @Value("${datasource1.username}")
    private String username1;
    @Value("${datasource1.password}")
    private String password1;
    @Value("${datasource1.driver-class-name}")
    private String driverClassName1;

    @Value(("${spring.datasource.druid.filters}"))
    private String filters;

    @Bean("dataSource")
    public DataSource dataSource() {
        try {
            DruidDataSource dataSource0 = new DruidDataSource();
            dataSource0.setDriverClassName(this.driverClassName0);
            dataSource0.setUrl(this.url0);
            dataSource0.setUsername(this.username0);
            dataSource0.setPassword(this.password0);
            dataSource0.setFilters(this.filters);

            DruidDataSource dataSource1 = new DruidDataSource();
            dataSource1.setDriverClassName(this.driverClassName1);
            dataSource1.setUrl(this.url1);
            dataSource1.setUsername(this.username1);
            dataSource1.setPassword(this.password1);
            dataSource1.setFilters(this.filters);

            //分库设置
            Map<String, DataSource> dataSourceMap = new HashMap<>(2);
            //添加两个数据库database0和database1
            dataSourceMap.put("ds0", dataSource0);
            dataSourceMap.put("ds1", dataSource1);

            // 配置 t_user 表规则
            TableRuleConfiguration userRuleConfiguration = new TableRuleConfiguration("t_user", "ds${0..1}.t_user${0..1}");
            // 配置分表规则
            userRuleConfiguration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",
                    UserShardingAlgorithm.tableShardingAlgorithm));
            // 配置分库规则
            userRuleConfiguration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", UserShardingAlgorithm.databaseShardingAlgorithm));
            // Sharding全局配置
            ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();

            TableRuleConfiguration orderRuleConfigration = new TableRuleConfiguration("t_order","ds0.t_order_${0..1}");
            orderRuleConfigration.setDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(new HintOrderAlgorithm()));
            orderRuleConfigration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("order_id",UserShardingAlgorithm.tableShardingAlgorithm));
            Collection<TableRuleConfiguration> tableRuleConfigs = shardingRuleConfiguration.getTableRuleConfigs();
            tableRuleConfigs.add(userRuleConfiguration);
            tableRuleConfigs.add(orderRuleConfigration);
            shardingRuleConfiguration.setDefaultDataSourceName("ds0");
            shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());
            // 创建数据源
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
            return dataSource;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

