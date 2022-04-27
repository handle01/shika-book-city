package com.parachute.shikabookcity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.shikabookcity.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author machi
 * @since 2022-04-15 23:57:08
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}

