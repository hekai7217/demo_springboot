package com.jutixueyuan.config;

import com.jutixueyuan.realm.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 黄药师
 * @date 2021-01-25 14:19
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *   shiro 集成 springboot 的配置类 环境搭建
 *
 */
//@Configuration
public class ShiroConfig01 {

    // 这里需要配置 shirofilter
    // 这里配置 shirofilter 继承中  提供了  shirofilterFactoryBean 工程类型  可以创建 shirofilter类注入ioc容器中
    // ioc的时候  xxxFactoryBean 工厂类型 整合第三方的Java类注入ioc中容器中

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 把securityManager 设置到  shiroFilterFactoryBean

//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
        return shiroFilterFactoryBean;
    }


    // securityManager
    // 之前 不是web项目    SecurityManager securityManager = factory.getInstance();
    // springboot 集成      这里的securityManager  ==> DefaultWebSecurityManager
    //
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置 自定义的realm
//        defaultWebSecurityManager.setRealm(customRealm());

        return defaultWebSecurityManager;
    }

    // 自定义realm
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

}
