package com.xjtushilei.controller;

/**
 * @author shilei
 * @Date 2017/6/1.
 */

import com.xjtushilei.domain.SysUser;
import com.xjtushilei.servive.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String idnex() {
        return "index";
    }


    @RequestMapping("/login")
    public String login(SysUser sysUser, Model model) {
        try {
            if (userService.validate(sysUser)) {
                // 若匹配，跳转到blog主页
                // 查询该用户下的所有博客

                return "index";
            } else {
                // 若不匹配，提示用户名或密码错误
                model.addAttribute("error", "用户名或密码错误！");
                return "login";
            }
        } catch (Exception e) {
            logger.error("登录出错！", e);
        }
        return "login";
    }


    @RequestMapping("register")
    public String register(SysUser sysUser, Model model) {
        if (userService.exits(sysUser)) {
            // 提示该用户已注册
            model.addAttribute("error", "用户名已存在！");
            return "register";
        } else {
            // 将用户信息存到数据库
            userService.save(sysUser);
            return "login";
        }
    }
}