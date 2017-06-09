package com.xjtushilei.schedule;

import com.xjtushilei.domain.AutoSignLog;
import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.job.SignThread;
import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.repository.UserInfoRepository;
import com.xjtushilei.utils.MailTemplate;
import com.xjtushilei.utils.mail.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author shilei
 * @Date 2017/6/2.
 */
@Component
public class SignJob {
    private final Logger logger = LoggerFactory.getLogger(SignJob.class);

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SignLogRepository signLogRepository;


    //    @Scheduled(cron = "0 10 3 1 * ?")
    //    public void execute() {
    //
    //        userInfoRepository.findAll().forEach(userinfo -> {
    //            String html = MailTemplate.tongzhiHtml.replace("weizhideyoujian", userinfo.getEmail());
    //            try {
    //                MailUtil.sendMail(userinfo.getEmail(),
    //                        "【自动签到系统】的【自助查询】",
    //                        html);
    //            } catch (MessagingException | IOException e) {
    //                logger.error("邮件发送失败！", e);
    //            }
    //        });
    //    }

    @Scheduled(cron = "0 0 8 ? * SAT")
    public void executer周报() {

        //获取第几周
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());

        Date date = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate() - 6); //6
        userInfoRepository.findAll().forEach(userinfo -> {

            List<AutoSignLog> list = signLogRepository.findByEmailAndLocalDateTimeAfter(userinfo.getEmail(), date);

            String jutixijie = "";
            for (AutoSignLog log : list) {
                jutixijie = jutixijie + log.mailString();
            }
            String html = MailTemplate.weekendHtml.replace("number", String.valueOf(list.size()));
            html = html.replace("jutixijie", jutixijie);
            html = html.replace("weizhideyoujian", userinfo.getEmail());

            try {
                MailUtil.sendMail(userinfo.getEmail(),
                        "【第" + calendar.get(Calendar.WEEK_OF_YEAR) + "周】 " + userinfo.getName() + "的自动签到周报",
                        html);
            } catch (MessagingException | IOException e) {
                logger.error("邮件发送失败！", e);
            }
        });
    }


    @Scheduled(cron = "0 10 8 * * ?")
    public void execute早上签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 15 * 60, signLogRepository, "早晨签到");
            signThread.start();
        });
    }

    @Scheduled(cron = "0 41 11 * * ?")
    public void execute早上签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30 * 60, signLogRepository, "早晨签退");
            signThread.start();
        });
    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void execute下午签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 15 * 60, signLogRepository, "下午签到");
            signThread.start();
        });
    }


    @Scheduled(cron = "0 41 17 * * ?")
    public void execute下午签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30 * 60, signLogRepository, "下午签退");
            signThread.start();
        });
    }

    @Scheduled(cron = "0 35 18 * * ?")
    public void execute晚上签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30 * 60, signLogRepository, "晚上签到");
            signThread.start();
        });
    }


    @Scheduled(cron = "0 10 22 * * ?")
    public void execute晚上签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30 * 60, signLogRepository, "晚上签退");
            signThread.start();
        });
    }

}
