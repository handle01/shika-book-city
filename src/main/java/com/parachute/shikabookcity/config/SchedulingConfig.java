package com.parachute.shikabookcity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * 定时任务线程池配置
 *
 * @author machi
 * @date 2022/05/04
 */
@Configuration
public class SchedulingConfig implements SchedulingConfigurer {
    /**
     * 配置任务
     *
     * @param taskRegistrar 任务注册
     * 所有的定时任务都放在一个线程池中, 定时任务启动时使用不同的线程
     *  swzhu
     *  2020/4/16 11:47
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个定时任务线程池,数量为5
        taskRegistrar.setScheduler(newScheduledThreadPool(5));
    }
}
