package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.SecuredResource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Repository
public class SecuredResourceRepositoryImpl implements SecuredResourceRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public SecuredResource getSecuredResource(String name) {

        Session session = sessionFactory.getCurrentSession();

        SecuredResource securedResource = (SecuredResource) session.createCriteria(SecuredResource.class).add(Restrictions.eq("name", name)).uniqueResult();

        return securedResource;
    }

    @Override
    @Transactional
    public List<SecuredResource> getSecuredResources() {

        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(SecuredResource.class);
        criteria.addOrder(Order.desc("sortOrder"));

        return criteria.list();
    }


    @Override
    @Transactional
    public void save(SecuredResource securedResource) {

        Session session = sessionFactory.getCurrentSession();
        session.save(securedResource);
    }
}
