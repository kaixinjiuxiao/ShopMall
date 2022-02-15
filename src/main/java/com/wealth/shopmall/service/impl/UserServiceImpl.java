package com.wealth.shopmall.service.impl;

import com.wealth.shopmall.entity.User;
import com.wealth.shopmall.mapper.UserMapper;
import com.wealth.shopmall.service.IUserService;
import com.wealth.shopmall.service.ex.InsertException;
import com.wealth.shopmall.service.ex.UserNameRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 处理用户数据的业务层实现类
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper mapper;

    @Override
    public void register(User user) {
        //获取用户名
        String username = user.getUsername();
        User u = mapper.queryUserByName(username);
        if (u != null) {
            throw new UserNameRepeatException("用户名已存在");

        }

        String salt = UUID.randomUUID().toString().toLowerCase();
        String md5Pwd = getMd5Password(user.getPassword(),salt);//获取加密后的密码
        user.setPassword(md5Pwd);
        user.setSalt(salt);//记录盐值
        user.setIsDelete(0);

        Date time = new Date();

        //补全数据
        user.setCreatedTime(time);
        user.setModifiedTime(time);
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        Integer i = mapper.register(user);
        if (i != 1) {
            throw new InsertException("注册异常，请重新尝试");
        }
    }

    /**
     * 获取加密密码
     * @param password 初始密码
     * @param salt 盐值
     * @return
     */
    private String getMd5Password(String password,String salt){
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password =   DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
