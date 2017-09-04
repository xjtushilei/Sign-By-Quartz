package com.xjtushilei.controller;

import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.utils.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.xjtushilei.schedule.SignJob.getWeekStartDate;

/**
 * @author shilei
 * @Date 2017/7/5.
 */
@RestController
public class Zhuanfa {


    @Autowired
    private SignLogRepository signLogRepository;

    @RequestMapping("/zhuanfa")
    public String zhuanfa111(String id) {
        String signUrl = PropertyUtil.getProperty("signurl");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(signUrl + "?id=" + id, String.class);
        return result;
    }

    @RequestMapping("/test")
    public String test1() {

        long nowTime = signLogRepository.countByEmailAndInfoAndLocalDateTimeAfter("619983341@qq.com", "随机不签到行为~",
                getWeekStartDate());
        return "" + nowTime;
    }
}
