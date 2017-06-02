package com.xjtushilei.schedule;

import com.xjtushilei.domain.AutoSignLog;
import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.job.SignThread;
import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    @Scheduled(cron = "0 15 8 * * ?")
    public void execute早上签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 10 * 60);
            signThread.start();
        });
        signLogRepository.save(new AutoSignLog(new Date(), "早晨签到", userInfoList.size()));
    }

    @Scheduled(cron = "0 41 11 * * ?")
    public void execute早上签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 20 * 60);
            signThread.start();
        });
        signLogRepository.save(new AutoSignLog(new Date(), "早晨签退", userInfoList.size()));
    }

    @Scheduled(cron = "0 3 14 * * ?")
    public void execute下午签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 10 * 60);
            signThread.start();
        });
        signLogRepository.save(new AutoSignLog(new Date(), "下午签到", userInfoList.size()));
    }


    @Scheduled(cron = "0 41 17 * * ?")
    public void execute下午签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 20 * 60);
            signThread.start();
        });
        signLogRepository.save(new AutoSignLog(new Date(), "下午签退", userInfoList.size()));
    }

    @Scheduled(cron = "0 35 18 * * ?")
    public void execute晚上签到() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30 * 60);
            signThread.start();
        });
        signLogRepository.save(new AutoSignLog(new Date(), "晚上签到", userInfoList.size()));
    }


    @Scheduled(cron = "0 10 22 * * ?")
    public void execute晚上签退() {

        List<AutoSignUserInfo> userInfoList = userInfoRepository.findAll();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 30 * 60);
            signThread.start();
        });
        signLogRepository.save(new AutoSignLog(new Date(), "晚上签退", userInfoList.size()));
    }

}
