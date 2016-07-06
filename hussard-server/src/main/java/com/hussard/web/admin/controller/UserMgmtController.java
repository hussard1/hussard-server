package com.hussard.web.admin.controller;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Controller
@RequestMapping("/admin/user")
public class UserMgmtController {

    @Autowired
    private UserService userService;


    @RequestMapping("/index")
    public String page(){
        return "/admin/index";
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<User> users = userService.getList();
        model.addAttribute("users", users);
        return "/admin/user/list";
    }
}
