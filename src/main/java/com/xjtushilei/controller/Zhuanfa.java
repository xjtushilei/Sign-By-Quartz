package com.xjtushilei.controller;

import com.xjtushilei.utils.PropertyUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author shilei
 * @Date 2017/7/5.
 */
@RestController
public class Zhuanfa {

    @RequestMapping("/zhuanfa")
    public String zhuanfa111(String id) {
        String signUrl = PropertyUtil.getProperty("signurl");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(signUrl + "?id=" + id, String.class);
        return result;
    }
}
