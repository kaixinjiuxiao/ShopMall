package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 *
 */
/*用户模块持久层*/
public interface UserMapper {

    /**
     * 注册 查询新的用户
     *
     * @param user
     * @return
     */
    Integer register(User user);


    /**
     * 通过用户名查询用户
     *
     * @param name
     * @return
     */
    User queryUserByName(String name);

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);


    /**
     * 修改用户密码
     *
     * @param uid
     * @param password     密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据uid查询用户数据
     *
     * @param uid
     * @return
     */
    User findUserByUid(Integer uid);

    /**
     * 修改用户信息
     * @param user 用户
     * @return
     */
    Integer updateUserInfoById(User user);
}
