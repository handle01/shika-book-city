package com.parachute.shikabookcity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.parachute.shikabookcity.dao")
@EnableScheduling

public class ShikaBookCityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShikaBookCityApplication.class, args);
    }

}
