package com.hussard.web.base.user.service;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Matthew on 2015-06-08.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUser(username);
        if (logger.isDebugEnabled()) {
            logger.debug("User that is logged in is " + user);
        }

        return user;
    }
}
