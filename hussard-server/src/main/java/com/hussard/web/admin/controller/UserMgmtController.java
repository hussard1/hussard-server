package com.hussard.web.admin.controller;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(@RequestParam(value = "search_word", defaultValue = "", required = false) String searchWord,
                       @RequestParam(value = "page_size", defaultValue = "10", required = false) int pageSize,
                       @RequestParam(value = "page_num", defaultValue = "1", required = false) int pageNum,
                       Model model){

        Long userCount = userService.getUserCount(searchWord);
        List<User> users = userService.getList(searchWord, pageSize, pageNum);
        model.addAttribute("users", users);
        model.addAttribute("userCount", userCount);
        return "/admin/user/list";
    }
}
