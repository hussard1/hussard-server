package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.Config;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 2016-02-16.
 */
@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Config> findConfigList() {
        Session session = sessionFactory.getCurrentSession();

        return session.createCriteria(Config.class).list();
    }

    @Override
    @Transactional
    public Config findConfigByBbsId(int bbsId) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Config.class);

        return (Config)criteria.add(Restrictions.eq("id", bbsId)).uniqueResult();
    }

    @Override
    @Transactional
    public void saveConfig(Config config) {
        Session session = sessionFactory.getCurrentSession();
        session.save(config);
    }

    @Override

    public void updateConfig(Config config) {
        Session session = sessionFactory.getCurrentSession();
        session.update(config);
    }
}
