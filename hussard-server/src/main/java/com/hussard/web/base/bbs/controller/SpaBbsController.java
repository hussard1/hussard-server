package com.hussard.web.base.bbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by user on 2015-10-15.
 */
@Controller
@RequestMapping(value = "/bbs/asdf")
public class SpaBbsController {
    private static final Logger logger = LoggerFactory.getLogger(SpaBbsController.class);

    @RequestMapping(value="/index1" , method = RequestMethod.GET)
    public String getList1(Model model){
        model.addAttribute("asd", "asd");
        logger.debug("aaaaaaaaa");
        return "bbs/bbs/list";
    }
}
