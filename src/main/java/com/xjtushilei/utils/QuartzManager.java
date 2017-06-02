package com.xjtushilei.utils;

import com.xjtushilei.job.SignJob;
import com.xjtushilei.job.UserInfo;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
public class QuartzManager {

    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserInfo userInfo;
    private Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


    private QuartzManager() throws SchedulerException {
        scheduler.start();
    }

    public static QuartzManager build() throws SchedulerException {
        return new QuartzManager();
    }

    public QuartzManager setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        return this;
    }

    private void setJob() {

    }

    private void setTrigger() {

    }

    public void start() {
        /**
         * 检测job是否存在，处理旧的job
         */
        for (int i = 0; i < 6; i++) {
            try {
                JobKey jobKey = JobKey.jobKey(userInfo.getName() + "-" + userInfo.getIdCard() + "-" + i, JOB_GROUP_NAME);
                if (scheduler.checkExists(jobKey)) {
                    logger.info("存在该job，开始进行删除");
                    //暂停任务
                    try {
                        scheduler.pauseJob(jobKey);
                    } catch (SchedulerException e) {
                        logger.error("任务停止失败！", e);
                    }
                    // 删除触发器
                    TriggerKey triggerKey = TriggerKey.triggerKey(userInfo.getName() + "-" + userInfo.getIdCard() + "-" + i,
                            TRIGGER_GROUP_NAME);
                    try {
                        scheduler.unscheduleJob(triggerKey);
                    } catch (SchedulerException e) {
                        logger.error("定时器<" + i + ">停止失败！", e);
                    }
                    // 删除job
                    scheduler.deleteJob(jobKey);
                }
            } catch (SchedulerException e) {
                logger.error("检查任务是否存在出错！", e);
            }
        }


        /**
         * 处理新的job,进行添加操作!
         */
        Class info = userInfo.getClass();
        try {
            for (int i = 0; i < 6; i++) {
                //添加job
                Method method = info.getDeclaredMethod("getError" + (i + 1));
                int error = (int) method.invoke(userInfo);
                JobDetail jobDetail = newJob(SignJob.class)
                        .withIdentity(userInfo.getName() + "-" + userInfo.getIdCard() + "-" + i, JOB_GROUP_NAME)
                        .usingJobData("name", userInfo.getName())
                        .usingJobData("email", userInfo.getEmail())
                        .usingJobData("idCard", userInfo.getIdCard())
                        .usingJobData("error", String.valueOf(error))
                        .storeDurably()
                        .build();
                scheduler.addJob(jobDetail, false);

                //添加触发器
                Method triggerMethod = info.getDeclaredMethod("getTime" + (i + 1));
                LocalTime localTime = (LocalTime) triggerMethod.invoke(userInfo);
                Trigger trigger1 = newTrigger()
                        .withIdentity(userInfo.getName() + "-" + userInfo.getIdCard() + "-" + i, TRIGGER_GROUP_NAME)
                        .startNow()
                        //                        .withSchedule(dailyAtHourAndMinute(localTime.getHour(), localTime.getMinute()))
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(30))
                        .forJob(jobDetail)
                        .build();
                scheduler.scheduleJob(trigger1);
            }
        } catch (SchedulerException e) {
            logger.error("任务添加失败！", e);
        } catch (NoSuchMethodException e) {
            logger.error("反射获得int失败2！", e);
        } catch (IllegalAccessException e) {
            logger.error("反射获得int失败3！", e);
        } catch (InvocationTargetException e) {
            logger.error("反射获得int失败4！", e);
        }


    }


}
