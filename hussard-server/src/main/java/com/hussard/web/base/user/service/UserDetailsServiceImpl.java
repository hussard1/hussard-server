package com.hussard.web.base.user.service;

import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.auth.repository.GroupRepository;
import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

        for(Group group : user.getGroups()){
            user.setAuthorities(group.getAuthorities());
        }

        return user;
    }
}
