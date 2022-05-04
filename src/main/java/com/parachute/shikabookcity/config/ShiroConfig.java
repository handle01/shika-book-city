package com.parachute.shikabookcity.config;

import com.parachute.shikabookcity.realm.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置
 *
 * @author machi
 * @date 2022/04/25
 */
@Configuration
public class ShiroConfig {


    /**
     * shiro过滤器工厂bean
     *
     * @param manager 经理
     * @return {@link ShiroFilterFactoryBean}
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager manager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(manager);
        Map<String,String> map = new HashMap<>(16);
        map.put("/main","authc");
        factoryBean.setFilterChainDefinitionMap(map);
        //设置登录页面
        factoryBean.setLoginUrl("/login");
        //未授权页面
        factoryBean.setUnauthorizedUrl("/unauth");
        return factoryBean;
    }


    /**
     * shiro管理
     *
     * @param jdbcRealm jdbc域
     * @return {@link DefaultWebSecurityManager}
     */
    @Bean
    public DefaultWebSecurityManager manager(@Qualifier("jdbcRealm") JdbcRealm jdbcRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(jdbcRealm);
        return manager;
    }

    /**
     * jdbc域
     *
     * @return {@link JdbcRealm}
     */
    @Bean
    public JdbcRealm jdbcRealm(){
        return new JdbcRealm();
    }
}