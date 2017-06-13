package com.hussard.web.admin.controller;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.service.UserService;
import com.hussard.web.base.util.PageNation;
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
    public String list(@RequestParam(value = "searchWord", defaultValue = "", required = false) String searchWord,
                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                       Model model){

        Long userCount = userService.getUserCount(searchWord);
        List<User> users = userService.getList(searchWord, pageSize, pageNum);

        PageNation pageNation = new PageNation(pageNum, pageSize, userCount);

        model.addAttribute("users", users);
        model.addAttribute("pageNation", pageNation);
        model.addAttribute("searchWord", searchWord);
        return "/admin/user/list";
    }
}
