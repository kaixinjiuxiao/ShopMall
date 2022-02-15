package com.wealth.shopmall.service;

import com.wealth.shopmall.entity.User;
import com.wealth.shopmall.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    IUserService service;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("李四");
            user.setPassword("123456");
            service.register(user);
            System.out.println("注册结果：成功");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
