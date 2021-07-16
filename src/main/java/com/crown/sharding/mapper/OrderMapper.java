package com.crown.sharding.mapper;

import com.crown.sharding.entity.Order;
import com.crown.sharding.entity.OrderResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luhaihui
 * @create 2021/3/12 下午12:58
 */
public interface OrderMapper {
    void save(Order order);

    Order getOrderById(@Param("orderId") Long orderId);

    List<OrderResult> getOrderUserById(@Param("userId")Long userId);
}
