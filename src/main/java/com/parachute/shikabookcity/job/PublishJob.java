package com.parachute.shikabookcity.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.parachute.shikabookcity.entity.Activity;
import com.parachute.shikabookcity.service.ActivityService;
import com.parachute.shikabookcity.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PublishJob {
//    0 0 0/1 1/1 * ? 每一小时执行一次
    @Autowired
    private ActivityService activityService;
    @Autowired
    private SalesService salesService;

    /**
     * 扫描数据库查询需要进行激活的活动
     * 定时任务1小时扫描一次
     */
    @Scheduled(cron = "0 0 0/1 1/1 * ? ")
    public void scanPublish(){
        List<Integer> activities = activityService.scanPublish(new Date());
        if (activities.size() != 0){
            publish(activities);
        }
    }

    /**
     * 进行激活
     *
     * @param activities 活动
     */
    private void publish(List<Integer> activities){
        LambdaUpdateWrapper<Activity> wrapper = Wrappers.lambdaUpdate();
        activities.forEach(activityId->{
            wrapper.eq(Activity::getId,activityId)
                    .set(Activity::getIsDie,0);
            activityService.update(wrapper);
            activityService.publish(activityId);
            wrapper.clear();
        });
    }

    /**
     * 扫描数据库删除已过期的活动
     * 定时任务1小时扫描一次
     */
    @Scheduled(cron = "0 0 0/1 1/1 * ? ")
    public void scanOverdue(){
        List<Integer> activities = activityService.scanOverdue(new Date());
        if (activities.size() != 0){
            delPublish(activities);
        }
    }

    /**
     * 删除已过期的方法
     *
     * @param activities 活动
     */
    private void delPublish(List<Integer> activities){
        LambdaUpdateWrapper<Activity> wrapper = Wrappers.lambdaUpdate();
        activities.forEach(activityId->{
            wrapper.eq(Activity::getId,activityId)
                    .set(Activity::getIsDie,1);
            activityService.update(wrapper);
            activityService.delPublish(activityId);
            wrapper.clear();
        });
    }



}
