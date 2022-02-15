package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

@SpringBootTest
//@RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    public void register(){
        User user= new User();
        user.setUsername("张三");
        user.setPassword("123456");
       Integer integer = mapper.register(user);
        System.out.println("注册结果：=="+integer);
    }

    @Test
    public void selectUserByname(){
        User user = mapper.queryUserByName("张三");
        System.out.println(user.toString());
    }
}
