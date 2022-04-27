package com.parachute.shikabookcity.config;/*
 *项目名: parachute-ssm
 *文件名: RestCofigration
 *创建者: 马驰
 *创建时间:2022/4/5 14:40
 *描述: TODO

 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 其他配置
 *
 * @author machi
 * @date 2022/04/25
 */
@Configuration
public class RestConfiguration {

    /**
     * 其他模板
     *
     * @return {@link RestTemplate}
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
