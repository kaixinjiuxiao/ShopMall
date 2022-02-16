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

    @Test
    public void login() {
        try {
            String name="麻蛋";
            String password = "123456";
            User u = service.login(name,password);
            System.out.println("登录成功=="+u.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePwd(){
        try {
            service.changePassword(6,"admin","1234567","987456");
            System.out.println("密码修改成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getUserById(){
        try {
            User user = service.getUserById(6);
            System.out.println("用户信息=="+user.toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo(){
        try {
            Integer uid = 8;
            String username ="麻蛋";

            User user =new User();
            user.setPhone("13574125698");
            user.setEmail("123456@163.com");
            user.setGender(0);
            service.changeInfo(uid,username,user);
            System.out.println("用户信息修改成");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
