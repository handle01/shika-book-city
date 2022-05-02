package com.parachute.shikabookcity.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.parachute.shikabookcity.config.CustomObjectMapper;
import com.parachute.shikabookcity.dao.UserDao;
import com.parachute.shikabookcity.entity.User;
import com.parachute.shikabookcity.service.UserService;
import com.parachute.shikabookcity.util.ImgtuUtil;
import com.parachute.shikabookcity.util.Result;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.SneakyThrows;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户服务impl
 * (User)
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-14 22:39:21
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CustomObjectMapper objectMapper;

    /**
     * 注册
     *
     * @param user 用户
     * @return {@link Result}
     */
    @SneakyThrows
    @Override
    public Result register(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        String pass = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        User user1 = super.baseMapper.selectOne(wrapper);
        //验证是否注册过
        if (user1 != null) {
            return Result.of(false, "该账号已经注册");
        }
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //获取用户登录信息
        UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
        // 通过ip获取其所属的地址
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + request.getRemoteHost() + "&json=true", String.class);
        String body = result.getBody();
        Map<String, String> map = objectMapper.readValue(body, new TypeReference<Map<String, String>>() {
        });

        String location = map.get("addr") + map.get("pro") + map.get("city") + map.get("region");
        int i = new Random().nextInt(100000);
        String nickName = "用户_" + i;
        //注入信息
        User user2 = new User();
        user2.setUserName(userName);//账号
        user2.setNickName(nickName);//用户名
        user2.setPassword(pass);//密码
        user2.setStatus("0");//是否冻结 ，0为不冻结
        user2.setLoginDate(new Date());//登录时间
        user2.setLoginIp(location);//登录地址ip
        user2.setIpaddr(request.getRemoteAddr());//登录地址
        user2.setCreateTime(new Date());//创建时间
        user2.setUpdateTime(new Date());//修改时间
        user2.setUpdateName("system");
        user2.setProfile("https://s1.ax1x.com/2022/04/17/LNdyM4.png");
        //存入数据库
        super.baseMapper.insert(user2);
        return Result.of(true, "注册成功");
    }

    /**
     * 登录
     *
     * @param user 用户
     */
    @SneakyThrows
    @Override
    public void login(User user) {
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        //获取用户登录信息
        UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));
        // 通过ip获取其所属的地址
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + request.getRemoteHost() + "&json=true", String.class);

        String body = result.getBody();
        Map<String, String> map = objectMapper.readValue(body, new TypeReference<Map<String, String>>() {
        });

        String location = map.get("addr") + map.get("pro") + map.get("city") + map.get("region");


        LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.set(User::getLoginDate, new Date())//登录时间
                .set(User::getIpaddr, request.getRemoteAddr())//登录地址
                .set(User::getLoginIp, location).eq(User::getUserName, user.getUserName());
        super.baseMapper.update(null, updateWrapper);

    }

    /**
     * 验证表单
     *
     * @param user 用户
     * @return {@link Result}
     */
    @Override
    public Result validateForm(User user) {
        if (user != null) {
            String userName = user.getUserName();
            String nickName = user.getNickName();
            String phone = user.getPhone();
            user.setUpdateTime(new Date());
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserName, userName);
            User one = super.baseMapper.selectOne(queryWrapper);
            queryWrapper.clear();
            //是否昵称重复
            if (!one.getNickName().equals(nickName)) {
                queryWrapper.eq(User::getNickName, nickName);
                List<User> nicknames = user.selectList(queryWrapper);
                if (nicknames.size() > 0) {
                    return Result.of(false, "昵称已被占用");
                }
            }
            queryWrapper.clear();
            //是否电话号码重复绑定
            if (!one.getPhone().equals(phone)) {
                queryWrapper.eq(User::getPhone, phone);
                List<User> phones = user.selectList(queryWrapper);
                if (phones.size() > 0) {
                    return Result.of(false, "号码已被绑定");
                }
            }
        }
        return null;
    }

    /**
     * 更新
     *
     * @param user 用户
     */
    @Override
    public void update(User user) {
        LambdaUpdateWrapper<User> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(User::getUserName, user.getUserName())
                .set(User::getNickName, user.getNickName())
                .set(User::getSex, user.getSex())
                .set(User::getPhone, user.getPhone())
                .set(User::getProfile, user.getProfile())
                .set(User::getUpdateTime, user.getUpdateTime())
                .set(User::getUpdateName,"用户本人");
        super.baseMapper.update(user, wrapper);
    }

    /**
     * 上传
     *
     * @param imgFile img文件
     * @return {@link String}
     */
    @Override
    public String upload(MultipartFile imgFile) {
        //随机文件名称防止覆盖
        String fileName = UUID.randomUUID().toString();

            String fileUrl = null;
            try {
                //上传文件到路过图床，返回图片Url到前端展示
                fileUrl = ImgtuUtil.upload(imgFile.getBytes(), fileName, ContentType.IMAGE_JPEG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return fileUrl;
    }

}