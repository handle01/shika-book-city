package com.parachute.shikabookcity.config;/*
 *项目名: shika-book-city
 *文件名: WebConfig
 *创建者: 马驰
 *创建时间:2022/4/15 20:06


 */

import com.parachute.shikabookcity.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 网络配置
 *
 * @author machi
 * @date 2022/04/25
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 登录拦截器
     */
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 添加拦截器
     *
     * @param registry 注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor);

    }
}
