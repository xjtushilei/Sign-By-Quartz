package com.xjtushilei.controller;

import com.xjtushilei.domain.AutoSignLog;
import com.xjtushilei.repository.SignLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author shilei
 * @Date 2017/6/5.
 */
@Controller
public class SignLog {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SignLogRepository signLogRepository;

    @RequestMapping("/log")
    public String register(@RequestParam(value = "email", defaultValue = "All") String email, Model model) {

        List<AutoSignLog> list;
        if (email.equals("All")) {
            list = signLogRepository.findALlLog(new PageRequest(0, 300));
        } else {
            list = signLogRepository.findByEmail(new PageRequest(0, 300), email);
        }
        model.addAttribute("list", list);
        model.addAttribute("email", email);
        return "history";
    }
}
