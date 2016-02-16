package com.hussard.web.base.user.repository;

import com.hussard.web.base.user.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Matthew on 2015-06-08.
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getUser(String username) {
        return null;
    }
}
