package com.parachute.shikabookcity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author machi
 * @date 2022/05/10
 */
@SpringBootApplication
@MapperScan("com.parachute.shikabookcity.dao")
@EnableScheduling
@EnableTransactionManagement
public class ShikaBookCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShikaBookCityApplication.class, args);
    }

}
