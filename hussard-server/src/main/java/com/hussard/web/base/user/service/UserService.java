package com.hussard.web.base.user.service;

import com.hussard.web.base.user.domain.User;

import java.util.List;

/**
 * Created by hussard on 2016. 7. 6..
 */
public interface UserService {

    List<User> getList(String searchWord, int pageSize, int pageNum);

    void save(User user);

    User getUser(String username);

    Long getUserCount(String searchWord);
}
