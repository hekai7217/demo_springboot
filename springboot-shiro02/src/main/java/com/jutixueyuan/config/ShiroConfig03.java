package com.jutixueyuan.config;

import com.jutixueyuan.realm.CustomRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 黄药师
 * @date 2021-01-25 14:19
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *   shiro 集成 springboot 的配置类 环境搭建
 *
 *   ShiroFilterFactoryBean 的配置的细节:
 *
 *   web应用中的资源：
 *
 *     01  静态资源基本要放行js,css,图片等
 *
 *     02  需要认证（登录）但是不需要权限，能访问的资源
             1)资源需要登录访问，跳转登录页面
             2)资源不要登录访问，直接放行
 *
 *    03   用户登录以后，还需要判断用户是否拥有有权限访问的资源
                1)有：直接放行访问的资源
                2)没有：跳转没有提示的页面

        shirofilter 中有中的拦截器.可以拦截指定的资源
        shirofilter 常见的过滤器

        过滤器使用别名                                   过滤器对应的类
        01  anon	org.apache.shiro.web.filter.authc.AnonymousFilter
                 不拦截,直接放行 （要放行js,css,图片等） 就用这个来配置

        02  authc	org.apache.shiro.web.filter.authc.FormAuthenticationFilter
                  表单过滤器  拦截请求， 跳转登录页面

        没有认证跳转到 登录页面的url 的配置
        shiroFilterFactoryBean.setLoginUrl("/user/loginPage");
 *
 */
//@Configuration
public class ShiroConfig03 {

    // 这里需要配置 shirofilter
    // 这里配置 shirofilter 继承中  提供了  shirofilterFactoryBean 工程类型  可以创建 shirofilter类注入ioc容器中
    // ioc的时候  xxxFactoryBean 工厂类型 整合第三方的Java类注入ioc中容器中

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 把securityManager 设置到  shiroFilterFactoryBean
//        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());

        // 跳转到 登录页面 的url的配置
        // 没有认证 访问 跳转到 登录的url
        shiroFilterFactoryBean.setLoginUrl("/user/loginPage");

        //配置 shirofilter
        // Map<key,value>
        // key 拦截的访问资源 一般是url
        // value 过滤器的别名称
        Map<String, String> filterChainDefinitionMap = new HashMap<>();

        // 配置规则的配置：
        // 从上往下配置

        // 如果上面的资源匹配 了 ,后面的配置不执行
        filterChainDefinitionMap.put("/images/a.jpg","anon");

        //配置匿名过滤器 静态资源不用拦截
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");

        // 配置了表单拦截器
        // 所有的请求都会拦截   跳转登录页面
        // 01 怎么直接到 转发到 login.jsp

        //02 可以不可以自定设置 一个loginUrl ?

        FormAuthenticationFilter a;
        filterChainDefinitionMap.put("/**","authc");

        // 设置过滤器的拦截规则
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

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
