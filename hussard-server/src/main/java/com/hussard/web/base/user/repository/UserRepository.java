package com.hussard.web.base.user.repository;

import com.hussard.web.base.user.domain.User;

/**
 * Created by Matthew on 2015-06-08.
 */
public interface UserRepository {

    User getUser(String username);
}
