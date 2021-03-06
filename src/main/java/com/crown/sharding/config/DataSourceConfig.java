package com.crown.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.crown.sharding.algorithm.HintOrderAlgorithm;
import com.crown.sharding.algorithm.UserShardingAlgorithm;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.core.rule.ShardingRule;
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

//    @Value("${datasource1.url}")
//    private String url1;
//    @Value("${datasource1.username}")
//    private String username1;
//    @Value("${datasource1.password}")
//    private String password1;
//    @Value("${datasource1.driver-class-name}")
//    private String driverClassName1;

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

//            DruidDataSource dataSource1 = new DruidDataSource();
//            dataSource1.setDriverClassName(this.driverClassName1);
//            dataSource1.setUrl(this.url1);
//            dataSource1.setUsername(this.username1);
//            dataSource1.setPassword(this.password1);
//            dataSource1.setFilters(this.filters);

            //????????????
            Map<String, DataSource> dataSourceMap = new HashMap<>(2);
            //?????????????????????database0???database1
            dataSourceMap.put("ds0", dataSource0);
//            dataSourceMap.put("ds1", dataSource1);

            // ?????? t_user ?????????
            TableRuleConfiguration userRuleConfiguration = new TableRuleConfiguration("t_user", "ds0.t_user_${0..1}");
            // ??????????????????
            userRuleConfiguration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("id",
                    UserShardingAlgorithm.tableShardingAlgorithm));
            // ??????????????????
//            userRuleConfiguration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("id", UserShardingAlgorithm.databaseShardingAlgorithm));
            //??????t_order
            TableRuleConfiguration orderRuleConfigration = new TableRuleConfiguration("t_order","ds0.t_order_${0..1}");
            orderRuleConfigration.setDatabaseShardingStrategyConfig(new HintShardingStrategyConfiguration(new HintOrderAlgorithm()));
            orderRuleConfigration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id",UserShardingAlgorithm.tableShardingAlgorithm));



            // Sharding????????????
            ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
            //????????????????????????
            shardingRuleConfiguration.setDefaultDataSourceName("ds0");
            //????????????????????????????????????
            shardingRuleConfiguration.setDefaultTableShardingStrategyConfig(new NoneShardingStrategyConfiguration());
            Collection<TableRuleConfiguration> tableRuleConfigs = shardingRuleConfiguration.getTableRuleConfigs();
            tableRuleConfigs.add(userRuleConfiguration);
            tableRuleConfigs.add(orderRuleConfigration);

            Collection<String> bindingTableGroups = shardingRuleConfiguration.getBindingTableGroups();
            bindingTableGroups.add("t_user,t_order");
            // ???????????????
            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
            return dataSource;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

