package com.wealth.shopmall.service.impl;

import com.wealth.shopmall.entity.User;
import com.wealth.shopmall.mapper.UserMapper;
import com.wealth.shopmall.service.IUserService;
import com.wealth.shopmall.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

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
        String md5Pwd = getMd5Password(user.getPassword(), salt);//获取加密后的密码
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

    @Override
    public User login(String username, String password) {
        User result = mapper.queryUserByName(username);
        checkUserExits(result);

        String salt = result.getSalt();
        if (StringUtils.isEmpty(salt)) {
            throw new PasswordErrorException("密码错误");
        }
        String pwd = result.getPassword();
        String md5Pwd = getMd5Password(password, salt);
        if (!pwd.equals(md5Pwd)) {
            throw new PasswordErrorException("密码错误");
        }
        //创建新的对象
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = mapper.findUserByUid(uid);
        checkUserExits(result);
        //获取盐值
        String alt = result.getSalt();
        //给用户输入的旧密码进行MD5加密
        String oldMd5Pwd = getMd5Password(oldPassword, alt);
        if (!result.getPassword().contentEquals(oldMd5Pwd)) {
            throw new PasswordErrorException("原密码错误，请重新输入");
        }

        //将新密码进行加密
        String newMd5Pwd = getMd5Password(newPassword, alt);
        result.setPassword(newMd5Pwd);
        result.setModifiedUser(username);
        result.setModifiedTime(new Date());
        Integer rows = mapper.updatePasswordByUid(uid, newMd5Pwd, username, new Date());
        if (rows != 1) {
            throw new UpdateException("修改错误，请联系管理员");
        }
    }

    @Override
    public User getUserById(Integer id) {
        User result = mapper.findUserByUid(id);
        checkUserExits(result);
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = mapper.findUserByUid(uid);
        checkUserExits(result);
        //补全数据
        user.setUid(uid);
        user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows = mapper.updateUserInfoById(user);
        if(rows!=1){
            throw new UpdateException("更新数据失败，请联系管理员");
        }
    }

    @Override
    public void updateAvatar(Integer uid, String avatar, String username) {
        User result = mapper.findUserByUid(uid);
        checkUserExits(result);
        Integer rows = mapper.updateUserAvatarById(uid,avatar,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据失败，请联系管理员");
        }
    }

    /**
     * 获取加密密码
     *
     * @param password 初始密码
     * @param salt     盐值
     * @return
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    private void checkUserExits(User user) {
        if (user == null) {
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
    }
}
