package com.crown.sharding.mapper;

import com.crown.sharding.entity.User;

public interface UserMapper {
    /**
     * 保存
     */
    void save(User user);

    /**
     * 查询
     * @param id
     * @return
     */
    User get(Long id);
}