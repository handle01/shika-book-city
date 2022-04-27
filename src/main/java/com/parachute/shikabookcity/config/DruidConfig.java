package com.parachute.shikabookcity.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 德鲁伊配置
 *
 * @author machi
 * @date 2022/04/25
 */
@Configuration
public class DruidConfig {
    /**
     *
     * 注入数据源
     *
     * @return {@link DataSource}
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    /**
     * 统计视图servlet
     * 配置监控
     *
     * @return {@link ServletRegistrationBean}
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new
                StatViewServlet(), "/druid/*");
        HashMap<String, String> map = new HashMap<>(2);
        map.put("loginUsername", "machi");
        map.put("loginPassword", "241760");
        bean.setInitParameters(map);
        return bean;
    }

    /**
     * 网络统计滤波器
     *
     * @return {@link FilterRegistrationBean}
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>
                ();
        bean.setFilter(new WebStatFilter());
        HashMap<String, String> map = new HashMap<>(8);
        map.put("exclusions", "*.js");
        bean.setInitParameters(map);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
