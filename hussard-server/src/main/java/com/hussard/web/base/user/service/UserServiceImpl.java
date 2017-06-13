package com.hussard.web.base.user.service;

import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hussard on 2016. 7. 6..
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getList(String searchWord, int pageSize, int pageNum){
        return userRepository.getList(searchWord, pageSize, pageNum);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    @Override
    public Long getUserCount(String searchWord) {
        return userRepository.getUserCount(searchWord);
    }

    @Override
    public User getUser(long id) {
        return userRepository.getUser(id);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
