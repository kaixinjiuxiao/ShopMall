package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.entity.User;
import com.wealth.shopmall.service.IUserService;
import com.wealth.shopmall.service.ex.InsertException;
import com.wealth.shopmall.service.ex.UserNameRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/register")
    //@ResponseBody 表示此方法的响应结果是以json格式进行响应返回给前端
    public HttpResult<Void > register(User user){
        HttpResult<Void>  result = new HttpResult<>();
        iUserService.register(user);
        return new HttpResult<Void>(OK,"注册成功");

    }
}
