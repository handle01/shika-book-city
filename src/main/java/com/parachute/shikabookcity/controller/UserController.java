package com.parachute.shikabookcity.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.parachute.shikabookcity.constant.Constant;
import com.parachute.shikabookcity.entity.User;
import com.parachute.shikabookcity.service.UserService;
import com.parachute.shikabookcity.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2022-04-14 22:39:21
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;
    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 登录
     *
     * @param user 用户信息
     * @return {@link Result}
     */
    @RequestMapping("login")
    public Result login(@RequestBody User user) {
        //获取用户名和密码
        String userName = user.getUserName();
        String password = user.getPassword();
        //密码加密
        String pass = DigestUtils.md5DigestAsHex(password.getBytes());
        //调用shiro框架实现认证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, pass);
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return Result.of(false, "用户名错误或不存在");
        } catch (IncorrectCredentialsException ice) {
            return Result.of(false, "密码错误");
        } catch (LockedAccountException lae) {
            return Result.of(false, "账户被冻结");
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            return Result.of(false, "未知错误");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        //返回用户基本信息到前端
        User one = userService.getOne(wrapper);
        String nickName = one.getNickName();
        String profile = one.getProfile();
        Integer id = one.getId();
        Map<String, Object> map = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        map.put("userName", userName);
        map.put("token", uuid);
        redisTemplate.opsForValue().set(Constant.LOGIN_TOKEN + userName, uuid);
        userService.login(user);
        map.put("nickName", nickName);
        map.put("profile", profile);
        map.put("id", id);
        return Result.of(true, "登录成功", map);
    }

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Result}
     */
    @RequestMapping("register")
    public Result register(@RequestBody User user) {
        if (user != null) {
            return userService.register(user);
        }
        return Result.of(false, "服务器异常");
    }


    /**
     * 修改用户信息提交
     *
     * @param user 用户
     * @return {@link Result}
     */
    @RequestMapping("submit")
    public Result submit(@RequestBody User user) {
        Result result = userService.validateForm(user);
        if (result != null) {
            return result;
        }
        try {
            userService.update(user);
            return Result.of(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.of(false, "服务器异常");
        }

    }

    /**
     * 上传
     *
     * @param imgFile img文件
     * @return {@link Result}
     */
    @RequestMapping("upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {

        try {String fileUrl = userService.upload(imgFile);
        return Result.of(true, "上传成功", fileUrl);
        } catch (Exception e) {
            return Result.of(false, "已经存在");

        }

    }


    /**
     * 注销
     *
     * @param request 请求
     * @return {@link Result}
     */
    @RequestMapping("logout")
    public Result logout(HttpServletRequest request) {
        String userName = request.getHeader("userName");
        //删除redis里的token
        if (redisTemplate.delete(Constant.LOGIN_TOKEN + userName)) {
            return Result.of(true, "登出成功");
        }
        return Result.of(false, "服务器异常");
    }

    @GetMapping
    public R selectAll(Page<User> page, User user) {
        return success(this.userService.page(page, new QueryWrapper<>(user)));
    }

    /**
     * 获取数据
     *
     * @param userName 用户名
     * @return {@link Result}
     */
    @RequestMapping("getData")
    public Result getData(@RequestBody String userName) {

        try {
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUserName, userName);
            //返回用户基本信息到前端
            User one = userService.getOne(wrapper);
            String profile = one.getProfile();
            String nickName = one.getNickName();
            String sex = one.getSex();
            String phone = one.getPhone();
            HashMap<String, String> map = new HashMap<>();
            map.put("profile", profile);
            map.put("nickName", nickName);
            map.put("sex", sex);
            map.put("phone", phone);
            return Result.of(true, null, map);
        } catch (Exception e) {
            return Result.of(false, "服务器异常");
        }


    }

}

