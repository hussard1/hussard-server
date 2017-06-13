package com.hussard.web.base.user.repository;

import com.hussard.web.base.user.domain.User;

import java.util.List;

/**
 * Created by Matthew on 2015-06-08.
 */
public interface UserRepository {

    User getUser(String username);

    void save(User user);

    List getList(String searchWord, int pageSize, int pageNum);

    Long getUserCount(String searchWord);

    User getUser(long id);

    void update(User user);
}
