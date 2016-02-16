package com.hussard.web.base.bbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 2015-10-16.
 */
@Controller
public class testController {

    private static final Logger logger = LoggerFactory.getLogger(testController.class);

    @RequestMapping(value="/aaaa")
    public void asdfasdf(){
        logger.info("asdfsfasdfasdfasdf");
    }
}
