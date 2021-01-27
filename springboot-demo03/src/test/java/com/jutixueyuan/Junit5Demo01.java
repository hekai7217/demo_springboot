package com.jutixueyuan;

import org.junit.jupiter.api.*;

/**
 * @author 黄药师
 * @date 2021-01-20 16:31
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *    Junit5介绍:
 *      什么是JUnit 5  是junit 最新版本  以前都是用的是 4.x
 *
 *    Junit5:
 *      组成:
 *      JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
 *      JUnit   Platform 平台 (基础发射测试框架在JVM上) 要做成平台 (在其他的语言 等可以用)
 *      Jupiter (JUnit 5中编写测试和扩展 用于基础的测试功能)
 *         测试功能都是在 Jupiter里面
 *      Vintage  兼容 junit3和junit4 的功能
 *
 *      注解:
 *
 *       @Test  import org.junit.jupiter.api.Test; 测试类,用在指定的方法上面
 *
 *       @DisplayName 声明测试名称
 *
 *       @BeforeEach    在每个test方法之前执行
 *       @AfterEach     在每个test方法之后执行
 *
 *      方法要静态
 *       @BeforeAll     在所有test方法之前执行
 *       @AfterAll      在所有test方法之后执行
 *
 *       @Disabled       忽略某个测试的方法
 *
 *      junit4 迁移成 junit5 有些注解 没有,会替换
 *
 *      https://junit.org/junit5/docs/current/user-guide/#overview-java-versions 文档查询
 *
 */
public class Junit5Demo01 {

    @Disabled
    @Test
    public void test(){
        System.out.println("Junit5Demo01.test");
    }

    @DisplayName("我是登录测试")
    @Test
    public void test02(){

        System.out.println("Junit5Demo01.test02");
    }
//
//    @BeforeEach
//    public void init(){
//        System.out.println("Junit5Demo01.init");
//    }
//
//    @AfterEach
//    public void destroy(){
//        System.out.println("Junit5Demo01.destroy");
//    }
//
//    @BeforeAll
//    public  static  void create(){
//        System.out.println("Junit5Demo01.create");
//    }
//
//    @AfterAll
//    public static  void aaa(){
//        System.out.println("Junit5Demo01.aaa");
//    }

    @RepeatedTest(5)
    @Test
    public void repeat(){

        System.out.println("Junit5Demo01.repeat");
    }
}
