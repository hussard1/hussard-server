package com.hussard.web.base.bbs.controller;

import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.service.BbsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by user on 2015-07-01.
 */

@Controller
@RequestMapping(value = "/bbs/admin")
public class BbsAdminController {

    private static final Logger logger = LoggerFactory.getLogger(BbsAdminController.class);

    @Autowired
    private BbsService bbsService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showlist(Model model) {

        if (bbsService.validAdmin()) {
            List<Config> configs = bbsService.findConfigList();
            model.addAttribute("configs", configs);
        }
        return "bbs/admin/list";
    }

    @RequestMapping(value = "/savebbs", method = RequestMethod.GET)
    public String showBbsDetail(Model model) {

        if (bbsService.validAdmin()) {}

        Config config = new Config();
        model.addAttribute("config", config);

        return "bbs/admin/form";
    }

    @RequestMapping(value = "/savebbs.{suffix}", method = RequestMethod.POST)
    public String saveBbsDetail(@PathVariable String suffix, Model model, @Valid Config config, BindingResult result) {

        if(result.hasErrors()){
            model.addAttribute("config", config);
            return "bbs/admin/form";
        }
        //if (bbsService.validAdmin()) {}

        bbsService.saveConfig(config);

        return "redirect:/bbs/admin/list." + suffix;
    }

    @RequestMapping(value = "/updatebbs", method = RequestMethod.GET)
    public String updateBbsDetail(Model model,@RequestParam("bbsid") int bbsId) {

        //if (bbsService.validAdmin()) {}

        Config config = bbsService.findConfigByBbsId(bbsId);
        model.addAttribute("config", config);

        return "/bbs/admin/updateform";
    }



    @RequestMapping(value = "/updatebbs.{suffix}", method = RequestMethod.POST)
    public String updateBbsDetail(@PathVariable String suffix, Model model, @Valid Config config, BindingResult result) {

        if(result.hasErrors()){
            model.addAttribute("config", config);
            return "bbs/admin/updateform";
        }
        //if (bbsService.validAdmin()) {}

        bbsService.updateConfig(config);

        return "redirect:/bbs/admin/list." + suffix;
    }
}
