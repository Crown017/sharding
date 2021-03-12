package com.crown.sharding.mapper;

import com.crown.sharding.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @author luhaihui
 * @create 2021/3/12 下午12:58
 */
public interface OrderMapper {
    void save(Order order);
    Order getOrderById(@Param("orderId") Long orderId);
}
