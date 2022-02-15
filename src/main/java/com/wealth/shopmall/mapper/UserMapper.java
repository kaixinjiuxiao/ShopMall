package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.User;

/*用户模块持久层*/
public interface UserMapper {

    /**
     * 注册 查询新的用户
     * @param user
     * @return
     */
    Integer register(User user);


    /**
     * 通过用户名查询用户
     * @param name
     * @return
     */
    User queryUserByName(String name);
}
