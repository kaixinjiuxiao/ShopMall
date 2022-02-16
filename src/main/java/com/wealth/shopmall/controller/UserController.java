package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.entity.User;
import com.wealth.shopmall.service.IUserService;
import com.wealth.shopmall.service.ex.InsertException;
import com.wealth.shopmall.service.ex.UserNameRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/register")
    //@ResponseBody 表示此方法的响应结果是以json格式进行响应返回给前端
    public HttpResult<Void> register(User user) {
        System.out.println(user.toString());
        iUserService.register(user);
        return new HttpResult<Void>(OK, "注册成功");
    }


    @RequestMapping("/login")
    public HttpResult<User> login(String username, String password, HttpSession session) {
        User user = iUserService.login(username, password);
        //登录成功后 将信息存在session中
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());
        //获取session中绑定的数据
        System.out.println("session中的uid==" + getUidFromSession(session));
        System.out.println("session中的username=" + getUsernameFromSession(session));
        return new HttpResult<User>(OK, "登录成功", user);
    }

    @RequestMapping("/change_password")
    public HttpResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        //从session中获取用户UID，Username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        iUserService.changePassword(uid, username, oldPassword, newPassword);
        return new HttpResult<>(OK, "修改成功");
    }

    @GetMapping("/get_user_info")
    public HttpResult<User> getUserById(HttpSession session) {
        Integer uid = getUidFromSession(session);
        User result = iUserService.getUserById(uid);
        return new HttpResult<User>(OK, "获取成功", result);
    }

    @RequestMapping("/change_user_info")
    public HttpResult<Void> changeUserInfo(User user, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        iUserService.changeInfo(uid, username, user);
        return new HttpResult<>(OK, "修改成功");
    }
}
