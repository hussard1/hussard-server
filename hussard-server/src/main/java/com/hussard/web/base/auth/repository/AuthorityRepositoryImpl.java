package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.Authority;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Repository
public class AuthorityRepositoryImpl implements AuthorityRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Authority getAuthority(String name) {
        Session session = sessionFactory.getCurrentSession();

        Authority authority = (Authority) session.createCriteria(Authority.class).add(Restrictions.eq("name", name)).uniqueResult();

        return authority;
    }

    @Override
    @Transactional
    public List<Authority> getAuthorities() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Authority.class).list();
    }

    @Override
    @Transactional
    public void save(Authority authority) {
        Session session = sessionFactory.getCurrentSession();
        session.save(authority);
    }
}
