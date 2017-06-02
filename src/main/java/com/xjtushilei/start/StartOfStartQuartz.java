package com.xjtushilei.start;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 服务启动执行的程序
 * 优先级：1>2
 */

@Component
@Order(value = 1)
public class StartOfStartQuartz implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void run(String... args) throws Exception {


        logger.info(">>>>>>>>>>>>>>>服务启动检查1，检查quartz是否启动<<<<<<<<<<<<<");
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        if (scheduler.isStarted()) {
            logger.info(">>>>>>>>>>>>>>>quartz服务已经启动<<<<<<<<<<<<<");
        } else {
            logger.info(">>>>>>>>>>>>>>>quartz服务没有启动，将进行启动！<<<<<<<<<<<<<");
            scheduler.start();
            if (scheduler.isStarted()) {
                logger.info(">>>>>>>>>>>>>>>quartz服务已经启动<<<<<<<<<<<<<");
            } else {
                logger.error(">>>>>>>>>>>>>>>quartz服务无法启动<<<<<<<<<<<<<");
            }
        }

        logger.info(">>>>>>>>>>>>>>>服务检查结束<<<<<<<<<<<<<");


    }


}

