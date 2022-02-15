package com.wealth.shopmall.service;

import com.wealth.shopmall.entity.User;

/**
 * 用户模块业务接口
 */
public interface IUserService {

    /**
     * 注册
     * @param user
     */
    void register(User user);
}
