package com.crown.sharding.entity;

/**
 * @author luhaihui
 * @create 2021/7/16 下午12:48
 */
public class OrderResult extends Order {
    private String name;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
