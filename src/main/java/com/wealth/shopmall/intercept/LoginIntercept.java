package com.wealth.shopmall.intercept;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginIntercept implements HandlerInterceptor {
    /**检测全局session对象中是否有uid数据，如果没有重定向到登录页面
     * @param request
     * @param response
     * @param handler
     * @return true 放行；false 拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object uid = session.getAttribute("uid");
        if(uid==null){//说明用户没有登录过，重定向到登录页面
            response.sendRedirect("/web/login.html");
            return  false;
        }
        return true;
    }

}
