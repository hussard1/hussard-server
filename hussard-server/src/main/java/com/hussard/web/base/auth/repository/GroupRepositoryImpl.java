package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.user.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Repository
public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Group getGroup(String name) {
        Session session = sessionFactory.getCurrentSession();

        Group group = (Group) session.createCriteria(Group.class).add(Restrictions.eq("name", name)).uniqueResult();

        return group;
    }

    @Override
    @Transactional
    public void save(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.save(group);
    }
}
