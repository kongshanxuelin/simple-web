package com.sumslack.web.simple.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sumslack.web.simple.config.BusinessConfig;
import com.sumslack.web.simple.constant.Constant;
import com.sumslack.web.simple.dto.LoginInfoDTO;
import com.sumslack.web.simple.exception.PageSessionMissedException;
import com.sumslack.web.simple.service.UserService;

@Controller
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private BusinessConfig businessConfig;

    @Autowired
    private UserService userService;

    @Value("${spring.profiles.active}")
    private String env;


    @GetMapping("/")
    public String home(HttpServletRequest request, Map<String, Object> model) throws PageSessionMissedException {
        return index(request, model);
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request, Map<String, Object> model) throws PageSessionMissedException {
        HttpSession session = request.getSession(true);
        LoginInfoDTO loginInfo = (LoginInfoDTO) session.getAttribute(Constant.LOGIN_SESSION_KEY);
        if (loginInfo == null) {
            throw new PageSessionMissedException("Please login.");
        }
        logger.info(String.format("Login user info: %s", loginInfo.toString()));
        model.put("loginInfo", loginInfo);
        model.put("env", env);
        return "index";
    }
}

