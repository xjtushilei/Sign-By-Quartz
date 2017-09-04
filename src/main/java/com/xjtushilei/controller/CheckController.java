package com.xjtushilei.controller;

import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.job.SignThread;
import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author shilei
 * @Date 2017/7/5.
 */
@RestController
public class CheckController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SignLogRepository signLogRepository;

    @GetMapping("onesign")
    public String check123(String type) {
        Random random = new Random(System.nanoTime());
        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 0, signLogRepository, type, random, 30);
            signThread.start();
        });
        return "一共执行了" + userInfoList.size() + " 个任务！";
    }


    @GetMapping("getsign")
    public List<AutoSignUserInfo> getall() {
        return userInfoRepository.findByAutoSignIsTrue();
    }

    @GetMapping("getemail")
    public List<AutoSignUserInfo> getemail() {
        return userInfoRepository.findBySendEmailIsTrue();
    }

    @GetMapping("hi")
    public String hi() {
        return "hi";
    }

}
