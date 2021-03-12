package com.crown.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author luhaihui
 * @create 2021/3/12 下午3:50
 */
public class HintOrderAlgorithm implements HintShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        Collection<String> collection = new ArrayList<>();
        for (Object names:availableTargetNames){
            if (StringUtils.hasText((String) names)){
                collection.add((String) names);
                return collection;
            }
        }

        return null;
    }
}
