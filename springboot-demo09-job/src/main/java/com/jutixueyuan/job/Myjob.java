package com.jutixueyuan.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 黄药师
 * @date 2021-01-23 11:03
 * @desc 巨梯学院 http://www.jutixueyuan.com
 * <p>
 * 定时的job
 * <p>
 * 定时的具体的业务逻辑
 * 01     @Scheduled(cron = "0 0 1 8 * ?")  发个短信 (定时任务的业务)
 * 02     @Scheduled(cron = "0 0 1 9 * ?")  扣款   (定时任务的业务)
 * 03     @Scheduled(cron = "0 0 1 18 * ?") 发信用卡账单
 */
public class Myjob implements Job {

    /**
     * 定时任务具体的执行的方法
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 这里的业务随时可以换成  发信用卡账单 / 扣款
        System.out.println("我是一个定时任务..." + getDate());

    }

    private String getDate() {

        return new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());

    }
}
