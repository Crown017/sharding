package com.crown.sharding.entity;

/**
 * @author luhaihui
 * @create 2021/3/12 下午12:57
 */
public class Order {
    private Long orderId;
    private Long userId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
