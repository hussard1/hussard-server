package com.hussard.web.base.auth.service;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hussard on 2017-06-14.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAuthorities() {
        return authorityRepository.getAuthorities();
    }

    @Override
    public Authority getAuthority(long id) {
        return authorityRepository.getAuthority(id);
    }
}
