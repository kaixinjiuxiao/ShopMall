package com.wealth.shopmall.config;

import com.wealth.shopmall.intercept.LoginIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;

/**
 * 处理器拦截器的注册
 */
@Configuration
public class LoginInterceptConfig implements WebMvcConfigurer {
    //拦截器对象
    HandlerInterceptor handlerInterceptor = new LoginIntercept();

    /**
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器对象
        HandlerInterceptor handlerInterceptor = new LoginIntercept();
        //配置白名单 存在list集合中
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("/bootstrap3/**");
        urlList.add("/css/**");
        urlList.add("/images/**");
        urlList.add("/js/**");
        urlList.add("/web/register.html");
        urlList.add("/web/login.html");
        urlList.add("/web/index.html");
        urlList.add("/users/register");
        urlList.add("/users/login");
        registry.addInterceptor(handlerInterceptor)
                .addPathPatterns("/**")//表示要拦截的url
                .excludePathPatterns(urlList);//白名单 要放行的url
    }
}
