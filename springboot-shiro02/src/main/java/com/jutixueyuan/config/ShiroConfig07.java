package com.jutixueyuan.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jutixueyuan.filter.MyFormAuthenticationFilter;
import com.jutixueyuan.realm.CustomPermsRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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

        03  logout	org.apache.shiro.web.filter.authc.LogoutFilter
                  登出的过滤器

        04 perms  org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
               权限过滤器


        没有认证跳转到 登录页面的url 的配置
        shiroFilterFactoryBean.setLoginUrl("/user/loginPage");

       customRealm.setCredentialsMatcher(credentialsMatcher());
 *     认证配置加密次数
 *
 *     新增加了 退出的操作:
 *       logout 退出后,做了一个页面跳转的动作
 *
 *         String redirectUrl = getRedirectUrl(request, response, subject);
 *         //try/catch added for SHIRO-298:
 *         try {
 *             subject.logout();
 *         } catch (SessionException ise) {
 *             log.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
 *         }
 *         issueRedirect(request, response, redirectUrl);
 *         return false;
 *
 *       想登出的操作 跳转到指定的页面 ?
 *         01 自己创建一个logout filter
 *              public LogoutFilter logoutFilter(){
 *
         *         LogoutFilter logoutFilter = new LogoutFilter();
         *
         *         // 页面 跳转 (登出的操作 实现页面的跳转 )
         *         logoutFilter.setRedirectUrl("/user/toLoginPage");
         *
         *         return logoutFilter;
         *     }
 *
 *         02 设置自己的规则 替换系统中默认的 logoutfilter
 *
 *            配置自己的 filters.put("logout",logoutFilter());
 *            替换系统的filter
 *
 *         03 controller 实现 页面的
 *
 *         @RequestMapping("toLoginPage")
 *         public String toLoginPage(){
 *             return "login";
 *         }
 *
 *        04 页面的跳转一定要放行
 *          filterChainDefinitionMap.put("/user/toLoginPage","anon");
 *
 *         权限的配置:
 *
 *         01 在 premsRealm 中获取权限的信息
 *
 *         02 过滤器中配置权限
 *              filterChainDefinitionMap.put("/user/stuPage","perms[stu:list]");
 *              filterChainDefinitionMap.put("/teacher/teacherPage","perms[teacher:list]");
 *
 *        03  授权失败 跳转到指定的没有权限的提示页面
 *              factoryBean.setUnauthorizedUrl("/unauthorized.html");
 *
 *       添加缓存
 *         添加依赖：
 *           <dependency>
         *     <groupId>org.apache.shiro</groupId>
         *     <artifactId>shiro-ehcache</artifactId>
         *     <version>1.5.0</version>
         * </dependency>
         * <dependency>
         *     <groupId>net.sf.ehcache</groupId>
         *     <artifactId>ehcache-core</artifactId>
         *     <version>2.6.0</version>
         * </dependency>
 *
 *        01 defaultWebSecurityManager中设置缓存
 *            defaultWebSecurityManager.setCacheManager(cacheManager());
 *
 *        02  创建    cacheManager() 对象
 *
 *        设置缓存:
 *          01 第一次是 realm中的收取数据从数据库中获取
 *
 *          02 第二次就是从cache缓存中获取
 *
 *
 *    01 rememberMe 功能 :
 *        解决 shiro 默认 把认证信息存储在 session, 关闭浏览器后 认证操作要重新开始
 *
 *       shiro的   rememberMe 就解决了这个问题
 *
 *    02 功能的实现:
 *      01 securityManger中设置 rememberMeManger
 *          rememberMeManger 管理存储cookie的信息
 *
 *      02 设置 rememberMe 的生命周期
 *
 *      03 表单提交的时候
 *              自动登录:<input type="checkbox" name="rememberMe"> <br>
 *             name的值必须是      rememberMe
 *
 *      04 配置 user拦截器 (实现记住我的功能)
 *         filterChainDefinitionMap.put("/index","user");
 *
 */
@Configuration
public class ShiroConfig07 {

    // 这里需要配置 shirofilter
    // 这里配置 shirofilter 继承中  提供了  shirofilterFactoryBean 工程类型  可以创建 shirofilter类注入ioc容器中
    // ioc的时候  xxxFactoryBean 工厂类型 整合第三方的Java类注入ioc中容器中

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 把securityManager 设置到  shiroFilterFactoryBean
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());

        // shiroFilterFactoryBean 可以设置自定义的filter

        //  Map<String, Filter> filters;
        // key 是filter的别名  value就是具体的filter
        Map<String, Filter> filters =new HashMap<>();
        // 可以要是这个 logout  把系统在filter 替换成自己的

        filters.put("logout",logoutFilter());

        //设置自己的表单过滤器 替换系统自带的过滤器
        filters.put("authc",myFormAuthenticationFilter());

        shiroFilterFactoryBean.setFilters(filters);

        // 跳转到 登录页面 的url的配置
        // 没有认证 访问 跳转到 登录的url
        shiroFilterFactoryBean.setLoginUrl("/user/loginPage");

        //配置登录成功的跳转 登录成功跳转到指定的页面
        // 如果第一次是 错误的请求url 跳转到登录页面后 登录成功后
        // 直接跳转到 错误的请求url页面

        shiroFilterFactoryBean.setSuccessUrl("/index");

        //没有权限的 跳转到提示页面
        // 如果配置了注解的处理 权限  /unauthorized.html 的配置会失效
//        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.html");

        //配置 shirofilter
        // Map<key,value>
        // key 拦截的访问资源 一般是url
        // value 过滤器的别名称
        Map<String, String> filterChainDefinitionMap = new HashMap<>();

        // 配置规则的配置：
        // 从上往下配置

        // 如果上面的资源匹配 了 ,后面的配置不执行
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/images/a.jpg","anon");

        //配置匿名过滤器 静态资源不用拦截
        filterChainDefinitionMap.put("/images/**","anon");
        filterChainDefinitionMap.put("/css/**","anon");

        //退出跳转到登录页面的请求放行
        filterChainDefinitionMap.put("/user/toLoginPage","anon");

        //放行权限提示 页面
        filterChainDefinitionMap.put("/unauthorized","anon");

        // 配置了表单拦截器
        // 所有的请求都会拦截   跳转登录页面
        // 01 怎么直接到 转发到 login.jsp
        //02 可以不可以自定设置 一个loginUrl ?

        // 登出的过滤器的配置
        LogoutFilter log;
        filterChainDefinitionMap.put("/logout","logout");

        //配置过滤器的权限
        // key是url   value是权限的配置
        // value的配置是 perms[权限]   perms是权限过滤器
        //系统中有的权限 "stu:list", "stu:create", "stu:delete", "teacher:list"

        // 请求  /emp/empPage 页面
        // [emp:list]我们的权限  和 权限控制的realm 中的权限 进行 比较
        // 如果没有权限  跳转到  /unauthorized.html

        // 问题:
        // 01 对每个访问的url都会进行配置  麻烦  注解
        // 02 每次访问 url都会去realm中查询 权限的资源  (这个资源都是放到数据库中 效率低)
        PermissionsAuthorizationFilter permissionsAuthorizationFilter;

//        filterChainDefinitionMap.put("/stu/stuPage","perms[stu:list]");
//        filterChainDefinitionMap.put("/teacher/teacherPage","perms[teacher:list]");
//        filterChainDefinitionMap.put("/emp/empPage","perms[emp:list]");


        /*记住我过滤器
        *  管理浏览器后 访问index 会自动登录
        *     访问index的时候 会把cookie存储的认证信息读取处理 进行认证操作
        *
        * */
        filterChainDefinitionMap.put("/index","user");

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
        defaultWebSecurityManager.setRealm(customRealm());

        // 设置缓存
        defaultWebSecurityManager.setCacheManager(cacheManager());
        
        //设置会话管理器 
        defaultWebSecurityManager.setSessionManager(sesssionManager());

        // 配置记住我的功能
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());

        return defaultWebSecurityManager;
    }

    /*配置记住我管理器*/
    @Bean
    public RememberMeManager rememberMeManager(){
        // cookie的管理器
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        // CookieRememberMeManager 内部操作的是 simpleCookie
        // simpleCookie 继承是 cookie (在javaweb的cookie上进行了增强)

        //设置Cookie信息
        cookieRememberMeManager.setCookie(cookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }
    //创建Cookie对象
    @Bean
    public Cookie cookie(){
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //仅仅http协议访问
        cookie.setHttpOnly(true);
        //设置cookie的保存时间 7天
        cookie.setMaxAge(3600 * 24 * 7);

        return cookie;
    }

    // 创建sessionManager
    @Bean
    public SessionManager sesssionManager() {

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置 session的 超时   milliseconds
        // session十秒钟销毁  认证信息在session中销毁了  再次访问index 页面 认证信息 要重写登录
        sessionManager.setGlobalSessionTimeout(1000 * 300);

        //  把 js的sessionidurl重写去掉
        sessionManager.setSessionIdUrlRewritingEnabled(false);

        return sessionManager;
    }

    // 配置 加密的realm
    @Bean
    public CustomPermsRealm customRealm(){

        CustomPermsRealm customRealm = new CustomPermsRealm();

        //要给realm 给加密配置器
        customRealm.setCredentialsMatcher(credentialsMatcher());


        return customRealm;
    }

    //配置加密器
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        // 加密规则
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 算列次数
        hashedCredentialsMatcher.setHashIterations(10);

        return hashedCredentialsMatcher;
    }

    /*配置Shiro方言，让Thymeleaf支持Shiro标签*/
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     *  01 创建一个logoutfiilter
     *  02 设置 一个 页面跳转的url
     */
    public LogoutFilter logoutFilter(){

        LogoutFilter logoutFilter = new LogoutFilter();

        // 页面 跳转 (登出的操作 实现页面的跳转 )
        logoutFilter.setRedirectUrl("/user/toLoginPage");

        return logoutFilter;
    }

    /**
     *  自己重写的过滤器替换 之前的 shiro系统自带的表单过滤器
     */
    public MyFormAuthenticationFilter myFormAuthenticationFilter(){
        return new MyFormAuthenticationFilter();
    }

    //设置Shiro框架对注解支持 需要用到aop的操作
    //
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager());
        return authorizationAttributeSourceAdvisor;

    }

    //创建对SpringMVC抛出异常处理解析器
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        Properties properties = new Properties();

        // 如果是这个错误 发送  这个请求  /unauthorized
        // 注意 ： 01  模板下面存在这个页面   02 过滤器放行  /unauthorized 请求

        properties.put("org.apache.shiro.authz.UnauthorizedException","/unauthorized");
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        simpleMappingExceptionResolver.setExceptionMappings(properties);

        return simpleMappingExceptionResolver;
    }

    /*设置Spring框架支持集成其他框架可以使用AOP*/
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /*设置可以让Shiro框架使用AOP为表现层创建代理（Shiro权限判断的注解全部在表现层）*/
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    /*配置缓存管理器*/
    @Bean
    public CacheManager cacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        //设置自定义缓存策略的配置文件
        //ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return  ehCacheManager;
    }
}