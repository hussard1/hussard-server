package com.hussard.web.base.settings.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hussard on 2016. 6. 14..
 */
@Controller
@RequestMapping("")
public class DefaultController {

    @RequestMapping("")
    public String index(){
        return "/index";
    }
}
