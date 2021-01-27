package com.jutixueyuan.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黄药师
 * @date 2021-01-23 9:47
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   定时任务的类
 */
//@Component
public class Jobscheduling {

    // 定时任务 执行的方法

    /**
     * 定时任务执行的规则
     *   规定的语法:
     *     cron 定时任务的语法:
     *      * * * * * *
     *      秒 分 时 日 月 周 年
     *
     *   "0/3 * * * * * 每3秒钟 执行一次这个方法
     *
     *   cron:
     *     定时任务的表达式:
     *        Cron表达式是一个字符串，分为6或7个域，每一个域代表一个含义
     *    (1） Seconds Minutes Hours Day Month Week Year
     *   （2） Seconds Minutes Hours Day Month Week
     *
     *    数据的范围:
     *       Seconds Minutes  (0 - 59)
     *       Hours  (0-23)
     *       Day    (1-31)
     *       Month  (1-12)
     *       Week   (1-7)
     *
     *    Cron表达式的时间字段除允许设置数值外 可以有偶一些特殊字符:
     *      星号(*)  可以在每个字段使用  (每分钟,每天,每月 等)
     *      问号（?） 可以在日期和 周中使用 没有值 就是一个占位符  周推荐使用这个
     *      减号(-)  表达一个范围， 所有的字段都可以使用
     *                Hours 1-3 1到3点
     *                Day   5-7 5到7号
     *      逗号(,)   表达一个列表值
     *                 Hours  2,5,7  2点 5点 7点
     *                 Day   5,10    5号 10 号
     *
     *      斜杠(/)  x/y表达一个等步长序列，x为起始值，y为增量步长值
     *              Seconds  0/5    从0 + 5秒钟
     *              Seconds  5/15   从5秒钟  + 15秒
     *                              20 / 35 50
     *                       星/7 等同  0/7  从 0 + 7
     *      L：该字符只在日期和星期字段中使用，代表"Last"的意思
     *            day 里面   月最后一天  28,29,30,31
     *            week 里面  周最后一天  7
     *
     *      @Scheduled(cron = "0 0 1 1 1 ?")      1月1号1点钟执行一次
     *      @Scheduled(cron = "0 0 1 1 1,6 ?")    1月6月的1号1点钟执行一次
     *      @Scheduled(cron = "0 0 1 1 1,4,7,10 ?")   1,4,7,10 的1号 1点钟
     *      @Scheduled(cron = "0 0 1 1 * ?")     每月的1号1点钟
     *      @Scheduled(cron="0 0 1 * * *")       每月的每天的1点钟
     *
     *      应用场景:
     *         花呗: 每个月 8 号 1点钟给你发信息催款
     *           @Scheduled(cron = "0 0 1 8 * ?")  发个短信 (定时任务的业务)
     *         花呗: 每个月 9 号 1点钟给你发信息催款
     *            @Scheduled(cron = "0 0 1 9 * ?")  扣款   (定时任务的业务)
     */
//    @Scheduled(cron = "0/3 * * * * *")
    public void job(){

        System.out.println("我是一个定时任务...");

        System.out.println(getDate());

    }

    private String getDate() {

        return new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());

    }


}
