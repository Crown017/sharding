package com.crown.sharding.mapper;

import com.crown.sharding.entity.Book;
import org.apache.ibatis.annotations.Param;

/**
 * @author luhaihui
 * @create 2021/3/11 下午6:50
 */
public interface BookMapper {
    void save(Book book);

    Book getBook(@Param("id") Long id);
}
