package com.xjtushilei.job;

import com.xjtushilei.utils.PropertyUtil;
import com.xjtushilei.utils.mail.MailUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
public class SignJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        RestTemplate restTemplate = new RestTemplate();

        String signUrl = PropertyUtil.getProperty("signurl");
        JobKey key = context.getJobDetail().getKey();

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        UserInfo userInfo = new UserInfo(
                dataMap.getString("name"),
                dataMap.getString("idCard"),
                dataMap.getString("email"),
                dataMap.getInt("error"));
        //        System.out.println(userInfo.getEmail());
        try {
            //            System.out.println(signUrl);
            TimeUnit.SECONDS.sleep(userInfo.getError());
            System.err.println(LocalTime.now() + "Instance:" + key + " name: " + userInfo.getName() + ", email: " + userInfo
                    .getEmail());
            String html = restTemplate.getForObject(signUrl + "?id=" + userInfo.getIdCard(), String.class);
            MailUtil.sendMail(userInfo.getEmail(), "[" + LocalDateTime.now() + "][" + userInfo.getName() + "]的签到提醒", html);
        } catch (InterruptedException e) {
            logger.error("延迟启动失败！", e);
        } catch (MessagingException e) {
            logger.error("邮件发送失败！", e);
        } catch (IOException e) {
            logger.error("读取邮件配置失败！", e);
        }

    }
}
