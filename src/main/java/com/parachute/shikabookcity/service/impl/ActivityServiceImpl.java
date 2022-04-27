package com.parachute.shikabookcity.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.parachute.shikabookcity.dao.ActivityDao;
import com.parachute.shikabookcity.entity.Activity;
import com.parachute.shikabookcity.service.ActivityService;
import com.parachute.shikabookcity.util.DateUtil;
import com.parachute.shikabookcity.util.Result;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * 活动服务impl
 * (Activity)表服务实现类
 *
 * @author machi
 * @date 2022/04/25
 * @since 2022-04-23 15:58:31
 */
@Service("activityService")
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, Activity> implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    /**
     * 获取进行中的活动数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Override
    public List<Activity> getNoDie(String userName) {
        List<Activity> noDie = activityDao.getNoDie(userName);
        noDie.forEach(activity -> {
            //注入类型信息
            Integer id = activity.getId();
            List<Integer> books = activityDao.getActivityBook(id);
            activity.setBooks(books);
        });
        setPublishTime(noDie);
        return noDie;
    }

    /**
     * 获取已结束的活动数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Override
    public List<Activity> getIsDie(String userName) {
        List<Activity> isDie = activityDao.getIsDie(userName);
        isDie.forEach(activity -> {
            //注入类型信息
            Integer id = activity.getId();
            List<Integer> books = activityDao.getActivityBook(id);
            activity.setBooks(books);
        });
        setPublishTime(isDie);
        return isDie;
    }

    /**
     * 获得未发布书籍数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Override
    public List<Activity> getPublish(String userName) {
        List<Activity> publish = activityDao.getPublish(userName);
        publish.forEach(activity -> {
            //注入类型信息
            Integer id = activity.getId();
            List<Integer> books = activityDao.getActivityBook(id);
            activity.setBooks(books);
        });
        setPublishTime(publish);
        return publish;
    }

    /**
     * 删除用户活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     */
    @Override
    public void delUserActivity(Integer userId, Integer activityId) {
        activityDao.delUserActivity(userId, activityId);
    }

    /**
     * 发布
     *
     * @param activityId       活动id
     * @param startTime        开始时间
     * @param activityDeadline 活动截止日期
     */
    @Override
    public void publish(Integer activityId, String startTime, String activityDeadline) {
        activityDao.publish(activityId, startTime, activityDeadline);
    }

    /**
     * 添加活动
     *
     * @param userId     用户id
     * @param activityId 活动id
     */
    @Override
    public void addUserActivity(Integer userId, Integer activityId) {
        activityDao.addUserActivity(userId, activityId);
    }

    /**
     * 删除活动包含书籍
     *
     * @param activityId 活动id
     */
    @Override
    public void delActivityBook(Integer activityId) {
        activityDao.delActivityBook(activityId);
    }

    /**
     * 逻辑删除活动包含书籍
     *
     * @param activityId 活动id
     */
    @Override
    public void delActivityBookLogic(Integer activityId) {
        activityDao.delActivityBookLogic(activityId);
    }

    /**
     * 添加活动包含书籍
     *
     * @param activityId 活动id
     * @param bookId     书id
     */
    @Override
    public void addActivityBook(Integer activityId, Integer bookId) {
        activityDao.addActivityBook(activityId, bookId);
    }

    /**
     * 获取未进行活动的数据
     *
     * @param userName 用户名
     * @return {@link List}<{@link Activity}>
     */
    @Override
    public List<Activity> getPending(String userName) {
        List<Activity> pending = activityDao.getPending(userName);
        pending.forEach(activity -> {
            //注入类型信息
            Integer id = activity.getId();
            List<Integer> books = activityDao.getActivityBook(id);
            activity.setBooks(books);
        });
        setPublishTime(pending);
       return pending;
    }

    /**
     * 扫描数据库，判断活动是否需要激活
     *
     * @param date 日期
     * @return {@link List}<{@link Integer}>
     */
    @Override
    public List<Integer> scanPublish(Date date) {
        return activityDao.scanPublish(date);
    }

    /**
     * 发布
     *
     * @param activityId 活动id
     */
    @Override
    public void publish(Integer activityId) {
        activityDao.publish(activityId);
    }

    /**
     * 扫描数据库，判断活动是否结束
     *
     * @param date 日期
     * @return {@link List}<{@link Integer}>
     */
    @Override
    public List<Integer> scanOverdue(Date date) {
        return activityDao.scanOverdue(date);

    }

    /**
     * 删除未发布活动
     *
     * @param activityId 活动id
     */
    @Override
    public void delPublish(Integer activityId) {
        activityDao.delPublish(activityId);
    }

    /**
     * 插入
     *
     * @param data 数据
     * @return {@link Result}
     */
    @SneakyThrows
    @Override
    public Result insert(Map data) {
        Object o = data.get("activity");
        Activity activity = JSON.parseObject(JSON.toJSONString(o), new TypeReference<Activity>() {
        });
        String s = (String) data.get("id");
        int id = Integer.parseInt(s);
        Result result = validateForm(activity);
        if (result != null) {
            return result;
        }
        //随机生成商品编码
        int i = new Random().nextInt(100000000);
        String commodityCode = Integer.toString(i);
        activity.setCommodityCode(commodityCode);
        //注入基本信息
        List<String> publishTime = activity.getPublishTime();
        Date startTime = DateUtil.String2Date(publishTime.get(0), "yyyy-MM-dd HH:mm:ss");
        Date activityDeadline = DateUtil.String2Date(publishTime.get(1), "yyyy-MM-dd HH:mm:ss");
        activity.setStartTime(startTime);
        activity.setActivityDeadline(activityDeadline);
        activity.setCreateTime(new Date());
        activity.setUpdateTime(new Date());
        activity.setUpdateName("system");
        save(activity);
        //建立用户与活动的联系
        activityDao.addUserActivity(id, activity.getId());
        return Result.of(true, "添加成功");
    }

    /**
     * 验证表单
     *
     * @param activity 活动
     * @return {@link Result}
     */
    @SneakyThrows
    @Override
    public Result validateForm(Activity activity) {
        //校验表单
        String cover = activity.getCover();//封面
        if (cover == null) {
            return Result.of(false, "没有上传图片");
        }
        String description = activity.getDescription();//简介
        if (description == null || description.length() == 0) {
            return Result.of(false, "简介为空");
        }
        List<String> publishTime = activity.getPublishTime();//出版时间
        if (publishTime == null) {
            return Result.of(false, "活动时间时间未选择");
        }
        Date date = DateUtil.String2Date(publishTime.get(0), "yyyy-MM-dd HH:mm:ss");

        if (date.compareTo(new Date()) < 0) {
            return Result.of(false, "活动时间不能小于当前时间");
        }
        if (activity.getBooks() == null) {
            return Result.of(false, "活动包含产品没有选择");
        }
        return null;

    }

    /**
     * 更新
     *
     * @param activity 活动
     */
    @SneakyThrows
    @Override
    public void update(Activity activity) {
        //sql
        List<String> publishTime = activity.getPublishTime();
        Date startTime = DateUtil.String2Date(publishTime.get(0), "yyyy-MM-dd HH:mm:ss");
        Date activityDeadline = DateUtil.String2Date(publishTime.get(1), "yyyy-MM-dd HH:mm:ss");
        LambdaUpdateWrapper<Activity> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(Activity::getId, activity.getId())
                .set(Activity::getActivityName, activity.getActivityName())
                .set(Activity::getPrice, activity.getPrice())
                .set(Activity::getDescription, activity.getDescription())
                .set(Activity::getCover, activity.getCover())
                .set(Activity::getUpdateName, "用户本人")
                .set(Activity::getUpdateTime, new Date())
                .set(Activity::getStartTime, startTime)
                .set(Activity::getActivityDeadline, activityDeadline);

        super.baseMapper.update(activity, updateWrapper);
        Integer activityId = activity.getId();
        //先删除书籍与活动的联系
        activityDao.delActivityBook(activityId);
        List<Integer> bookIds = activity.getBooks();
        bookIds.forEach(bookId -> {
            //添加书籍与活动的联系
            activityDao.addActivityBook(activityId, bookId);
        });
    }

    /**
     * 设定发布时间
     *
     * @param activities 活动
     */
    @Override
    public void setPublishTime(List<Activity> activities) {
        activities.forEach(activity->{
            ArrayList<String> publishTime = new ArrayList<>();
            try {
                String startTime = DateUtil.Date2String(activity.getStartTime());
                String activityDeadline = DateUtil.Date2String(activity.getActivityDeadline());
                publishTime.add(startTime);
                publishTime.add(activityDeadline);
                activity.setPublishTime(publishTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }
}

