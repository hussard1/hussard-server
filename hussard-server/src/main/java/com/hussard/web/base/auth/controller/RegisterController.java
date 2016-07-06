package com.hussard.web.base.auth.controller;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by hussard on 2016. 7. 6..
 */
@Controller
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "/auth/register";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(Model model){
        return "/auth/login";
    }

}
