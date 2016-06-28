package com.hussard.web.base.user.repository;

import com.hussard.web.base.user.domain.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Matthew on 2015-06-08.
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User getUser(String username) {
        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();

        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
}
