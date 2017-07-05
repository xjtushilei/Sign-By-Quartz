package com.xjtushilei.controller;

import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.job.SignThread;
import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<AutoSignUserInfo> userInfoList = userInfoRepository.findByAutoSignIsTrue();
        userInfoList.forEach(userInfo -> {
            SignThread signThread = new SignThread(userInfo, 1, signLogRepository, type);
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
