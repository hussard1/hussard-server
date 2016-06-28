package com.hussard.web.base.settings.controller;

import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.service.BbsService;
import com.hussard.web.base.settings.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 2016-02-17.
 */
@Controller
@RequestMapping("/settings/init")
public class initController {

    @Autowired
    private InitService initService;

    @Autowired
    private BbsService bbsService;

    @RequestMapping("")
    public String init(){
        return "/settings/index";
    }

    @RequestMapping("/users")
    public String users() {

        initService.createDefaultAuthority();
        initService.createDefaultGroup();
        initService.createDefaultUser();

        initService.createDefaultSecuredResource();

        return "redirect:/settings/init";
    }



    @RequestMapping("/bbs")
    public String bbs(Model model){

        Config config = new Config();

        config.setBbsName("공지사항");
        config.setBbsDesc("공지사항 게시판 입니다.");
        config.setPerPage(10);
        config.setReplyYn(1);
        config.setReadAuth(1);
        config.setWriteAuth(1);

        bbsService.saveConfig(config);

        return "redirect:/settings/init";
    }


}
