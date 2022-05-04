package com.parachute.shikabookcity.interceptor;/*
 *项目名: parachute-ssm
 *文件名: LoginInterseptor
 *创建者: 马驰
 *创建时间:2022/4/6 11:16


 */


import com.parachute.shikabookcity.config.CustomObjectMapper;
import com.parachute.shikabookcity.constant.Constant;
import com.parachute.shikabookcity.constant.LoginConstant;
import com.parachute.shikabookcity.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 登录拦截器
 *
 * @author machi
 * @date 2022/05/04
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Autowired
    CustomObjectMapper customObjectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查拦截的URL有没有携带token
        //获取请求名 也就是/login
        String servletPath = request.getServletPath();
        //不拦截主页及登陆注册请求
        if (LoginConstant.LOGIN.equals(servletPath) || LoginConstant.INDEX.equals(servletPath) || LoginConstant.REGISTER.equals(servletPath)){
            return true;
        }
        String userName = request.getHeader("userName");
        //获取redis中的token与请求携带的token进行对比
        Object o = redisTemplate.opsForValue().get(Constant.LOGIN_TOKEN+userName);
        String token = request.getHeader("token");
        if (o != null && o.equals(token)) {
            //对比成功给token增加存活时间，
            redisTemplate.opsForValue().set(Constant.LOGIN_TOKEN+userName,token,3, TimeUnit.DAYS);
            return true;
        } else {
            //401代表认证失败
            ResponseEntity<Result> res = ResponseEntity.status(401).body(Result.of(false,"您未登录！"));
            response.setStatus(401);
            response.getWriter().write(customObjectMapper.writeValueAsString(res));
            return false;
        }

    }
}
