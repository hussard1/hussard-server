package com.hussard.web.base.auth.service;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.domain.Group;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hussard on 2017-06-14.
 */
public interface AuthorityService {

    List<Authority> getAuthorities();

    Authority getAuthority(long id);
}
