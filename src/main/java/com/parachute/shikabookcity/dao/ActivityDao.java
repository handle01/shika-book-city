package com.parachute.shikabookcity.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.parachute.shikabookcity.entity.Activity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 活动刀
 * (Activity)表数据库访问层
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-23 15:58:30
 */
@Mapper
public interface ActivityDao extends BaseMapper<Activity> {

    /**
     * 获取活动包含的书籍
     *
     * @param id id
     * @return {@link List}<{@link Integer}>
     */
    @Select("SELECT b.id,b.name " +
            "FROM activity_book ab,book b " +
            "WHERE ab.book_id = b.id and ab.activity_id = #{id}")
    List<Integer> getActivityBook(@Param("id") Integer id);

    /**
     * 获取活动包含的书籍
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Select("SELECT a.id,a.activity_name,a.price,a.description,a.cover,a.activity_deadline,a.start_time " +
            "from user u,user_activity ua,activity a " +
            "WHERE u.id = ua.user_id and a.id = ua.activity_id and a.is_die = 0 and a.del_flag = 0 and u.user_name = #{userName}")
    List<Activity> getNoDie(@Param("userName") String userName);

    /**
     * 获取已结束的活动
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Select("SELECT a.id,a.activity_name,a.price,a.description,a.cover,a.activity_deadline,a.start_time " +
            "from user u,user_activity ua,activity a " +
            "WHERE u.id = ua.user_id and a.id = ua.activity_id and a.is_die = 1 and a.del_flag = 0 and u.user_name = #{userName}")
    List<Activity> getIsDie(@Param("userName") String userName);

    /**
     * 获取未发布的活动
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Select("SELECT a.id,a.activity_name,a.price,a.description,a.cover,a.activity_deadline,a.start_time " +
            "from user u,user_activity ua,activity a " +
            "WHERE u.id = ua.user_id and a.id = ua.activity_id and a.is_publish = 0 and a.del_flag = 0 and u.user_name =#{userName}")
    List<Activity> getPublish(@Param("userName") String userName);

    /**
     * 获取未进行活动数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Select("SELECT a.id,a.activity_name,a.price,a.description,a.cover,a.activity_deadline,a.start_time \n" +
            "from user u,user_activity ua,activity a \n" +
            "WHERE u.id = ua.user_id and a.id = ua.activity_id and a.is_publish = 1 and a.del_flag = 0 and is_die=2 and u.user_name = #{userName}")
    List<Activity> getPending(@Param("userName") String userName);

    /**
     * 删除活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     */ //删除活动与用户的联系
    @Update("UPDATE user_activity ua set del_flag = 1 WHERE ua.user_id = #{userId} and ua.activity_id = #{activityId}")
    void delUserActivity(@Param("userId") Integer userId, @Param("activityId") Integer activityId);

    /**
     * 发布活动
     *
     * @param activityId       活动id
     * @param startTime        开始时间
     * @param activityDeadline 活动截止日期
     */
    @Insert("INSERT INTO  publish (activity_id,start_time,activity_deadline) VALUES ( #{activityId},#{startTime},#{activityDeadline}) ")
    void publish(@Param("activityId") Integer activityId,@Param("startTime")  String startTime,@Param("activityDeadline")String activityDeadline);

    /**
     * 添加活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     */
    @Insert("INSERT INTO user_activity (user_id,activity_id) VALUES (#{userId},#{activityId})")
    void addUserActivity(@Param("userId") Integer userId,@Param("activityId") Integer activityId);

    /**
     * 删除活动包含的商品
     *
     * @param activityId 活动id
     */
    @Delete("DELETE from activity_book WHERE activity_id = #{activityId}")
    void delActivityBook(Integer activityId);

    /**
     * 逻辑删除删除活动包含的商品
     *
     * @param activityId 活动id
     */
    @Delete("UPDATE activity_book SET del_flag = 1 WHERE activity_id = #{activityId}")
    void delActivityBookLogic(Integer activityId);

    /**
     * 添加活动包含的商品
     *
     * @param activityId 活动id
     * @param bookId     书id
     */
    @Insert("INSERT INTO activity_book (activity_id,book_id) VALUES (#{activityId},#{bookId})")
    void addActivityBook(@Param("activityId") Integer activityId,@Param("bookId") Integer bookId);

    /**
     * 查询活动开始时间是否小于当前时间
     *
     * @param date 日期
     * @return {@link List}<{@link Integer}>
     */
    @Select("SELECT p.activity_id FROM publish p WHERE  del_flag = 0 and start_time < #{date}")
    List<Integer> scanPublish(Date date);

    /**
     * 将字段改为发布状态
     *
     * @param activityId 活动id
     */
    @Update("update publish set del_flag = 2 where activity_id = #{activityId}")
    void publish(Integer activityId);

    /**
     * 查询活动结束时间小于当前时间
     *
     * @param date 日期
     * @return {@link List}<{@link Integer}>
     */
    @Select("SELECT p.activity_id FROM publish p WHERE del_flag = 2 and activity_deadline < #{date}")
    List<Integer> scanOverdue(Date date);

    /**
     * 删除活动
     *
     * @param activityId 活动id
     */
    @Update("update publish set del_flag = 1 where activity_id = #{activityId}")
    void delPublish(Integer activityId);
}

