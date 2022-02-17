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

    /**
     * 登录
     * @param username 账号
     * @param password 密码
     */
    User login(String username,String password);

    /**
     * 修改用户密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 获取用户信息
     * @param id  用户id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 修改用户信息
     * @param uid
     * @param username
     * @param user
     */
    void changeInfo(Integer uid,
                    String username,
                    User user);

    /**
     * 修改用户头像
     * @param uid  用户ID
     * @param avatar 头像地址
     * @param username 用户名
     */
    void updateAvatar(Integer uid,
                      String avatar,
                      String username);
}
