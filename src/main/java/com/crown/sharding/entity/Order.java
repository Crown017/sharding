package com.crown.sharding.entity;

/**
 * @author luhaihui
 * @create 2021/3/12 下午12:57
 */
public class Order {
    private Long orderId;
    private Integer userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
