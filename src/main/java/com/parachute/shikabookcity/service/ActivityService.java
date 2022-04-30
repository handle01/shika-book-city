package com.parachute.shikabookcity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.parachute.shikabookcity.entity.Activity;
import com.parachute.shikabookcity.util.Result;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 活动服务
 * (Activity)表服务接口
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-23 15:58:31
 */
public interface ActivityService extends IService<Activity> {


    /**
     *
     * 获取正在进行中的活动
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    List<Activity> getNoDie(String userName);

    /**
     * 获取正在已结束的活动
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    List<Activity> getIsDie(String userName);

    /**
     * 获得发布活动数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    List<Activity> getPublish(String userName);

    /**
     * 删除用户活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     */
    void delUserActivity(Integer userId, Integer activityId);

    /**
     * 发布活动
     *
     * @param activityId       活动id
     * @param startTime        开始时间
     * @param activityDeadline 活动截止日期
     */
    void publish(Integer activityId, String startTime,String activityDeadline);

    /**
     * 添加用户活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     */
    void addUserActivity(Integer userId, Integer activityId);

    /**
     * 删除活动包含书籍
     *
     * @param activityId 活动id
     */
    void delActivityBook(Integer activityId);

    /**
     * 逻辑删除活动包含书籍
     *
     * @param activityId 活动id
     */
    void delActivityBookLogic(Integer activityId);

    /**
     * 添加活动包含书籍
     *
     * @param activityId 活动id
     * @param bookId     书id
     */
    void addActivityBook( Integer activityId,Integer bookId);

    /**
     * 获取未进行活动数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    List<Activity> getPending(String userName);

    /**
     * 定时任务扫描数据库查看活动信息，判断是否要进行激活
     *
     * @param date 日期
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> scanPublish(Date date);

    /**
     * 发布活动
     *
     * @param activityId 活动id
     */
    void publish(Integer activityId);

    /**
     * 定时任务扫描数据库查看活动信息，判断是否结束活动
     *
     * @param date 日期
     * @return {@link List}<{@link Integer}>
     */
    List<Integer> scanOverdue(Date date);

    /**
     * 删除未发布活动
     *
     * @param activityId 活动id
     */
    void delPublish(Integer activityId);

    /**
     * 插入
     *
     * @param data 数据
     * @return {@link Result}
     */
    Result insert(Map data);

    /**
     * 验证表单
     *
     * @param activity 活动
     * @return {@link Result}
     */
    Result validateForm(Activity activity);

    /**
     * 更新
     *
     * @param activity 活动
     */
    void update(Activity activity);

    /**
     * 设定发布时间
     *
     * @param activities 活动
     */
    void setPublishTime(List<Activity> activities);

    void delActivity(Integer userId, Integer activityId);
}

