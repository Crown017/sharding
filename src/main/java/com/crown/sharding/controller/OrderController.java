package com.crown.sharding.controller;

import com.crown.sharding.entity.Order;
import com.crown.sharding.entity.OrderResult;
import com.crown.sharding.mapper.BookMapper;
import com.crown.sharding.mapper.OrderMapper;
import com.crown.sharding.mapper.UserMapper;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luhaihui
 * @create 2021/7/16 下午12:54
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Resource
    SnowflakeShardingKeyGenerator userKeyGenerator;

    @Resource
    private UserMapper userMapper;

    @Resource
    private BookMapper bookMapper;

    @Resource
    private OrderMapper orderMapper;

    @GetMapping("createOrder")
    @ResponseBody
    public String createOrder(@RequestParam("userId") Long userId){
        Order order = new Order();
        order.setOrderId((Long) userKeyGenerator.generateKey());
        order.setUserId(userId);
        orderMapper.save(order);
        return "success";
    }


    @GetMapping("getOrderInfo/{orderId}")
    @ResponseBody
    public Order getOrderInfo(@PathVariable("orderId") Long order){
        Order orderById = orderMapper.getOrderById(order);
        return orderById;
    }


    @GetMapping("getUserOrder/{userId}")
    @ResponseBody
    public List<OrderResult> getUserOrder(@PathVariable("userId") Long userId){
        List<OrderResult> orderUserById = orderMapper.getOrderUserById(userId);
        return orderUserById;
    }
}
