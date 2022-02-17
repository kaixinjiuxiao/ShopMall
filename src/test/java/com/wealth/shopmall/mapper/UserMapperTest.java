package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.User;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;
import java.util.Date;

@SpringBootTest
//@RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    public void register() {
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        Integer integer = mapper.register(user);
        System.out.println("注册结果：==" + integer);
    }

    @Test
    public void selectUserByname() {
        User user = mapper.queryUserByName("张三");
        System.out.println(user.toString());
    }

    @Test
    public void login() {
        String name = "张三";
        String password = "123456";
        User u = mapper.login(name, password);
        System.out.println(u.toString());
    }
    @Test
    public void updatePasswordByUid() {
        mapper.updatePasswordByUid(8,"456789","admin",new Date());
    }

    @Test
    public void uodateInfo(){
        User user = new User();
        user.setUid(6);
        user.setPhone("15012345678");
        user.setEmail("156789698@qq.com");
        user.setGender(1);
        user.setModifiedUser("admin");
        user.setModifiedTime(new Date());
        mapper.updateUserInfoById(user);

    }

    @Test
    public void updateAvatar(){
        String url="static/images/index/user.jpg";
        mapper.updateUserAvatarById(6,url,"管理员",new Date());

    }
}
