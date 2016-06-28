package com.hussard.web.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Controller
@RequestMapping("/admin")
public class UsersController {

    @RequestMapping("/index")
    public String page(){
        return "/admin/index";
    }
}
