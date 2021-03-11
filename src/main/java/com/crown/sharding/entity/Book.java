package com.crown.sharding.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author luhaihui
 * @create 2021/3/11 下午6:47
 */
@Data
public class Book {

    private Long id;
    private String bookName;
    private Integer price;
    private String pub;
    private Date releaseTime;
}
