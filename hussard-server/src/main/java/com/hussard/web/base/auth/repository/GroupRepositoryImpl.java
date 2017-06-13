package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.Group;
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
public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Group getGroup(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (Group) session.createCriteria(Group.class).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    @Transactional
    public void save(Group group) {
        Session session = sessionFactory.getCurrentSession();
        session.save(group);
    }

    @Override
    @Transactional
    public List<Group> getGroups() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Group.class).list();
    }

    @Override
    @Transactional
    public Group getGroup(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Group) session.createCriteria(Group.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
}
