package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.Authority;

/**
 * Created by hussard on 2016. 6. 28..
 */
public interface AuthorityRepository {

    Authority getAuthority(String name);

    void save(Authority authority);
}
