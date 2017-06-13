package com.hussard.web.base.user.repository;

import com.hussard.web.base.user.domain.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        return (User) session.createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
    }

    @Override
    @Transactional
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> getList(String searchWord, int pageSize, int pageNum) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria = criteria.setFetchMode("authorities", FetchMode.SELECT)
                .setFetchMode("groups", FetchMode.SELECT)
                .addOrder(Order.desc("created"));
        criteria = (searchWord.equals("")) ? criteria : criteria.add(Restrictions.like("username", searchWord, MatchMode.ANYWHERE));

        return criteria.setFirstResult((pageNum-1)*pageSize)
                    .setMaxResults(pageSize)
                    .list();
    }

    @Override
    @Transactional
    public Long getUserCount(String searchWord) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(User.class);
        criteria = criteria.setFetchMode("authorities", FetchMode.SELECT)
                           .setFetchMode("groups", FetchMode.SELECT);
        criteria = (searchWord.equals("")) ? criteria : criteria.add(Restrictions.like("username", searchWord, MatchMode.ANYWHERE));
        return (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();

    }

    @Override
    @Transactional
    public User getUser(long id) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    @Transactional
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }
}
