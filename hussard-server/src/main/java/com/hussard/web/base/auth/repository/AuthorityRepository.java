package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.Authority;

import java.util.List;

/**
 * Created by hussard on 2016. 6. 28..
 */
public interface AuthorityRepository {

    Authority getAuthority(String name);

    List<Authority> getAuthorities();

    void save(Authority authority);

    Authority getAuthority(long id);
}
