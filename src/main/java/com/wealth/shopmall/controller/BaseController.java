package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.service.ex.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层基类
 */

public class BaseController {
    /**
     * 操作成功的状态码
     */
    public static final int OK = 200;


    /**
     * 请求处理方法 这个方法的返回值是需要传递给前端的数据
     * 自动将异常传递给此方法的参数列表上
     *
     * @return
     */
    @ExceptionHandler(ServiceException.class)//用于统一处理抛出的异常
    public HttpResult<Void> handleException(Throwable e) {
        HttpResult<Void> result = new HttpResult<>(e);
        if (e instanceof UserNameRepeatException) {
            result.setCode(1001);
            result.setMessage("用户名已经存在");
        } else if (e instanceof InsertException) {
            result.setCode(1002);
            result.setMessage("数据库操作异常");
        } else if (e instanceof UserNotFoundException) {
            result.setCode(1003);
            result.setMessage("用户名不存在");
        } else if (e instanceof PasswordErrorException) {
            result.setCode(1004);
            result.setMessage("密码错误");
        } else if(e instanceof UpdateException){
            result.setCode(1002);
            result.setMessage("数据库更新异常");
        }
        return result;
    }

    /**
     * 获取session中的uid
     *
     * @param session session对象
     * @return 当前登录用户的UID的值
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取登录用户的用户名
     * @param session
     * @return
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString()
                ;
    }
}
