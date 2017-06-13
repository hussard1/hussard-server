package com.hussard.web.admin.controller;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.auth.service.AuthorityService;
import com.hussard.web.base.auth.service.GroupService;
import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.service.UserService;
import com.hussard.web.base.util.PageNation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Controller
@RequestMapping("/admin/user")
public class UserMgmtController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private AuthorityService authorityService;


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

    @RequestMapping(method = RequestMethod.GET, value = "/view")
    public String view(@RequestParam(value = "id") long id, Model model){
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/admin/user/view";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String add(User user, Model model){
        List<Group> groups = groupService.getGroups();
        List<Authority> authorities = authorityService.getAuthorities();
        model.addAttribute("groups", groups);
        model.addAttribute("authorities", authorities);
        model.addAttribute("user", new User());
        return "/admin/user/form";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String register(@RequestParam(value = "group") long[] groupIds,
                           @RequestParam(value = "authority") long[] authorityIds,
                           User user, Model model){
        Set<Group> groups = new HashSet<>();
        for(long groupId : groupIds){
            groups.add(groupService.getGroup(groupId));
        }
        user.setGroups(groups);

        Set<Authority> authorities = new HashSet<>();
        for(long authorityId : authorityIds){
            authorities.add(authorityService.getAuthority(authorityId));
        }
        user.setAuthorities(authorities);

        userService.save(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    public String update(@RequestParam(value = "id") long id, Model model){
        User user = userService.getUser(id);
        List<Group> groups = groupService.getGroups();
        List<Authority> authorities = authorityService.getAuthorities();

        List<Long> userGroupIds = new ArrayList<>();
        for(Object o : user.getGroups().toArray()){
            userGroupIds.add(((Group)o).getId());
        }

        List<Long> userAuthorityIds = new ArrayList<>();
        for(Object o : user.getAuthorities().toArray()){
            userAuthorityIds.add(((Authority)o).getId());
        }

        model.addAttribute("user", user);
        model.addAttribute("groups", groups);
        model.addAttribute("userGroupIds", userGroupIds);
        model.addAttribute("userAuthorityIds", userAuthorityIds);
        model.addAttribute("authorities", authorities);
        return "/admin/user/update";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String modify(@RequestParam(value = "group") long[] groupIds,
                         @RequestParam(value = "authority") long[] authorityIds,
                         User user, Model model){
        Set<Group> groups = new HashSet<>();
        for(long groupId : groupIds){
            groups.add(groupService.getGroup(groupId));
        }
        user.setGroups(groups);

        Set<Authority> authorities = new HashSet<>();
        for(long authorityId : authorityIds){
            authorities.add(authorityService.getAuthority(authorityId));
        }
        user.setAuthorities(authorities);

        userService.update(user);

        return "redirect:/admin/user/view?id="+user.getId();
    }
}
