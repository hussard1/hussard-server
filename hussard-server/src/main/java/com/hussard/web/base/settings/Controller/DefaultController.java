package com.hussard.web.base.settings.controller;

import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.service.BbsService;
import com.hussard.web.base.settings.service.InitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 2016-02-17.
 */
@Controller
@RequestMapping("")
public class DefaultController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping({"", "index"})
    public String init(){
//        logger.info("");
        return "/index";
    }

}
