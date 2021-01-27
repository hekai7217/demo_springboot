package com.jutixueyuan.springboot_demo02.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 黄药师
 * @date 2021-01-20 14:43
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   获取application.properties 中需要的属性的值
 *
 *   MyConfig 这个类中的属性 和 application 属性不一样 ,
 *     设置前缀
 *
 *   @ConfigurationProperties 会报错, 编译器报错
 *
 *      编译期告诉我们 把 MyConfig 注入到ioc容器中
 *
 *   这里 不用给  @Component
 *     @EnableConfigurationProperties({MyConfig.class}) 也可以
 *
 *      开启 自定义配置的 properties
 *
 *      @Import(EnableConfigurationPropertiesRegistrar.class)
 *         在ioc容器中注入 EnableConfigurationPropertiesRegistrar 这类
 *          01 扫描  有 ConfigurationProperties 注解标记的类
 *          02 把这些类注入到ioc容器中
 *
 *   01 对象注入ioc容器中的集中方式?
 *
 *    01 xml方式配置
 *    02 注解 + 扫描
 *    03 试下 BeanFactory 接口
 *
 *    04 @import 可以直接把类注入到ioc容器中
 *
 */
@ConfigurationProperties(prefix = "my.customer")
public class MyConfig {

    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
