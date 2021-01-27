package com.jutixueyuan;

import com.jutixueyuan.job.Myjob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 黄药师
 * @date 2021-01-23 11:07
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@SpringBootTest
public class JobTest {

    /**
     *   quartz 的核心
     *
     *    job   定时任务的业务
     *
     *    jobDetail   任务详细 (具体的可执行的调度任务 /任务调度和策略 )
     *
     *    Trigger  调度的参数
     *
     *    Scheduler 任务调用容器
     *       把  jobDetail ,Trigger   进行关联
     *
     */
    /**
     *  把 Scheduler 注入进来
     *
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 添加 jobDetail
     */
    @Test
    public void addJob(){

        // 01 创建 jobDetail
        // 不是直接new 通过 JobBuilder 构建

        // 把  JobDetail 和 job 进行关联
        JobDetail jobDetail = JobBuilder
                .newJob(Myjob.class)
                .withIdentity("my-job","my-job-group")  //给 jobDetail 给id和组
        .build();

        // 02 创建 Trigger 任务触发器  (调度的参数)
        // newTrigger 创建一个任务触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                // 给任务调度设置 调度规则
                //  cron 时间:  0/5 * * * * ? 每5秒中执行一次   这里的时间执行可以随时进行 修改

                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .withIdentity("my-trigger","my-trigger-group")
                .build();


        // 03  通过  Scheduler 把  jobDetail / Trigger 进行关联
        try {

            // 会存储到 mysql数据中
            scheduler.scheduleJob(jobDetail, trigger);

        // 04 启动 定时任务
            scheduler.start();

            // 给一个死循环 查看 定时任务的执行的 打印

            while (true){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     *  删除定时任务
     */
    @Test
    public void testDelJob(){

        // 01 拿到 jobkey
        JobKey jobKey = new JobKey("my-job", "my-job-group");

        // 02 通过任务调度容器删除
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 定时任务的修改
     *   修改的是 触发器 ( 任务调用的参数  )
     */
    @Test
    public void testJobupdate() throws SchedulerException {

        // 修改触发器
        // 01  要拿到触发器 的key
        // 这里的key 和 之前的 trigger 设置一样的name和group
        TriggerKey triggerKey = TriggerKey.triggerKey("my-trigger","my-trigger-group");

        //通过 scheduler 获取之前的 trigger
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 02 设置 触发器的参数
        // 创建一个新的trigger
        trigger = TriggerBuilder.newTrigger()
                // 设置 triggerkey
                 .withIdentity(triggerKey)
                // 设置更新 的 cron 任务调用度 的 时间
                 .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                 .build();

        // 03 修改触发器
        scheduler.rescheduleJob(triggerKey,trigger);

    }

    /**
     * 暂停  任务调用
     */
    @Test
    public void testPauseJob(){

        // 01  创建 jobkey
        JobKey jobKey = new JobKey("my-job", "my-job-group");

        // 02 用任务暂停方法就可以
        try {
            scheduler.pauseJob(jobKey);
            System.out.println(" 调度任务暂停  ");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 重启 任务调用
     */
    @Test
    public void testresumeJob(){

        // 01  创建 jobkey
        JobKey jobKey = new JobKey("my-job", "my-job-group");
        // 02 用任务重启方法就可以
        try {
            scheduler.resumeJob(jobKey);
            System.out.println(" 调度任重启  ");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
