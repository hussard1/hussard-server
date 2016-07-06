package com.hussard.web.base.user.service;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.enterprise.event.Observes;
import java.util.List;

/**
 * Created by hussard on 2016. 7. 6..
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getList(){
        return userRepository.getList();
    }

}
