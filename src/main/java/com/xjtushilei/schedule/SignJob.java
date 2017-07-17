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
import java.util.Random;

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

    private Random random = new Random(System.nanoTime());



    //    @Scheduled(fixedRate = 5000)
    //    public void test() {
    //
    //        userInfoRepository.findByAutoSignIsTrue().forEach(userinfo -> {
    //            System.out.println("findByAutoSignIsTrue:"+userinfo);
    //        });
    //
    //        userInfoRepository.findBySendEmailIsTrue().forEach(userinfo -> {
    //            System.out.println("findBySendEmailIsTrue:"+userinfo);
    //        });
    //    }

    @Scheduled(cron = "0 0 8 ? * SAT")
    public void executer周报() {

        //获取第几周
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());

        Date date = new Date(new Date().getYear(), new Date().getMonth(), new Date().getDate() - 5); //6
        userInfoRepository.findBySendEmailIsTrue().forEach(userinfo -> {

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


    @Scheduled(cron = "0 10 8 ? * MON-FRI")
    public void execute早上签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 17, signLogRepository, "早晨签到", random);
            signThread.start();
        });
    }

    @Scheduled(cron = "0 42 11 ? * MON-FRI")
    public void execute早上签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 40, signLogRepository, "早晨签退", random);
            signThread.start();
        });
    }

    @Scheduled(cron = "0 5 14 ? * MON-FRI")
    public void execute下午签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 12, signLogRepository, "下午签到", random);
            signThread.start();
        });
    }


    @Scheduled(cron = "0 43 17 ? * MON-FRI")
    public void execute下午签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30, signLogRepository, "下午签退", random);
            signThread.start();
        });
    }

    @Scheduled(cron = "0 50 18 ? * MON-FRI")
    public void execute晚上签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30, signLogRepository, "晚上签到", random);
            signThread.start();
        });
    }


    @Scheduled(cron = "0 11 22 ? * MON-FRI")
    public void execute晚上签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 40, signLogRepository, "晚上签退", random);
            signThread.start();
        });
    }

}
