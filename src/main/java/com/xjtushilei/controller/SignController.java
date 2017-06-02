package com.xjtushilei.controller;

/**
 * @author shilei
 * @Date 2017/6/1.
 */

import com.xjtushilei.job.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("update")
    public String register(UserInfo userInfo, Model model) {
        if (userInfo.getIdCard().length() != 10) {
            model.addAttribute("error", "卡号不正确，请检查长度！");
            return "index";
        } else if (userInfo.getError1() < 0
                || userInfo.getError2() < 0
                || userInfo.getError3() < 0
                || userInfo.getError4() < 0
                || userInfo.getError5() < 0
                || userInfo.getError6() < 0) {
            model.addAttribute("error", "随机数不能为负，请检查！");
            return "index";
        } else {

        }

        return "index";
    }
}