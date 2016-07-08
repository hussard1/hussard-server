package com.hussard.web.base.auth.controller;

import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.auth.service.GroupService;
import com.hussard.web.base.user.domain.Country;
import com.hussard.web.base.user.domain.Language;
import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hussard on 2016. 7. 6..
 */
@Controller
@RequestMapping("/auth")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "/auth/register";
    }


    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String register(User user, Model model){

        user.setLanguage(Language.ko);
        user.setCountry(Country.KR);
        Set<Group> groups = new HashSet<>();
        groups.add(groupService.getGroup("USER_GROUP"));
        user.setGroups(groups);

        userService.save(user);
        return "redirect:/auth/login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkUsername")
    @ResponseBody
    public Map checkUserName(@RequestParam(value = "username") String username){
        Map<String, String> resultMap = new HashMap<>();
        User user = userService.getUser(username);

        resultMap.put("username", username);

        if(user == null) {
            resultMap.put("result", "success");
            resultMap.put("result_code", "300");
            resultMap.put("message", "new username");
        }else{
            resultMap.put("result", "failure");
            resultMap.put("result_code", "400");
            resultMap.put("message", "duplicated username");
        }

        return resultMap;
    }
}
