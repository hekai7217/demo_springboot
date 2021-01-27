package com.jutixueyuan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 黄药师
 * @date 2021-01-22 10:38
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   logback   springboot 中 不强制依赖一个日志
 *     推荐使用  logback
 *
 *   logback   是log4j的升级版, 还可以 实现 logback原生实现了SLF4J API
 *
 *   怎么使用logback:
 *     只需要 在springboot项目中添加
 *        logback.xml  (用的是xml配置 )
 *             官方网站上面有 可以直接把 log4j的配置文件转成  logback.xml 的配置文件
 *
 *    日志配置文件的三要素:
 *
 *     01 日志等级
 *         error/warn/info/debug
 *         高等级的日志不会打印低等级的日志error > warn > info > debug
 *
 *     02 日志的输出位置
 *         控制台输出   console
 *         文件输出     file
 *
 *
 *     03 输入的布局 layout
 *         信息输出: 时间/类名等
 *         格式: 格式世间
 *
 *
 *
 *
 */
@Controller
public class IndexController {

    // 拿到日志器

    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @ResponseBody
    @RequestMapping("demo01")
    public String hello01(){

        logger.info("hello01 info");
        logger.debug("hello01 debug");

        return "hello01";

    }

    @ResponseBody
    @RequestMapping("demo02")
    public String hello02(){

        logger.info("hello02 info");
        logger.debug("hello02 debug");

        return "hello02";

    }

}
