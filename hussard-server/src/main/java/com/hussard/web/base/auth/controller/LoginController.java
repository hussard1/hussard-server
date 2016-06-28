package com.hussard.web.base.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by drago on 2015-11-08.
 */
@Controller
@RequestMapping("/auth/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping("")
    public String page(@RequestParam(value = "login_error", required = false) String error, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        if (error != null) {
            model.addAttribute("error", messageSource.getMessage("auth.login.message.fail", null, RequestContextUtils.getLocale(request)));
            logger.error("Authentication error: " + session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION));
        }

        return "auth/login";
    }
}
