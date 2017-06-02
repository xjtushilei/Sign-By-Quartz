package com.xjtushilei.start;

import com.xjtushilei.job.UserInfo;
import com.xjtushilei.utils.QuartzManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


/**
 * 服务启动执行的程序
 * 优先级：1>2
 */

@Component
@Order(value = 2)
public class StartOfCreateMysqlTable implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>服务启动检查2，开始检查数据库操作<<<<<<<<<<<<<");

        try {
            jdbcTemplate.queryForObject("SELECT COUNT(*) FROM QRTZ_JOB_DETAILS", Integer.class);
            logger.info(">>>>>>>>>>>>>>>存在定时操作的相关表<<<<<<<<<<<<<");
        } catch (Exception e) {
            logger.error(">>>>>>>>>>>>>>>不存在表，请自己创建！创建文件在resource/quartz中<<<<<<<<<<<<<");
            throw new Exception(">>>>>>>>>>>>>>>不存在表，请自己创建！创建文件在resource/quartz中<<<<<<<<<<<<<");
        }
        QuartzManager.build().setUserInfo(new UserInfo("小明", "1494720355", "619983341@qq.com", LocalTime.now(), LocalTime.now(),
                LocalTime.of(23, 33), LocalTime.of(23, 34), LocalTime.of(23, 33), LocalTime.now()
                , 1))
                .start();
        //        quartzService.addJob(new UserInfo("小2","2","2222222222@qq.com")).addTrigger(new UserInfo("小222","2222","2222222222@qq.com")).build();
        logger.info(">>>>>>>>>>>>>>>检查数据库操作结束<<<<<<<<<<<<<");


    }


}

