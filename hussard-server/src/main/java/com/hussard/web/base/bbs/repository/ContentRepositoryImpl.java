package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.Config;
import com.hussard.web.base.bbs.domain.Content;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 2016-02-16.
 */
@Repository
public class ContentRepositoryImpl implements ContentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Content> findContentList(int bbsId, int pageNum, int perPage, int searchMode, String searchContent) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Content.class);
        criteria.add(Restrictions.eq("bbsId", bbsId));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult((pageNum-1)* perPage);
        criteria.setMaxResults(perPage);
        return criteria.list();
    }

    @Override
    @Transactional
    public long findContentCount(int bbsId, int searchMode, String searchContent) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Content.class);
//        criteria.add(Restrictions.eq("id", bbsId));
        return (long)criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @Transactional
    public void saveContent(Content content) {
        Session session = sessionFactory.getCurrentSession();
        session.save(content);
    }

    @Override
    @Transactional
    public Content findContentById(int contentId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Content.class);
        criteria.add(Restrictions.eq("id", contentId));

        return (Content) criteria.uniqueResult();
    }

    @Override
    @Transactional
    public void updateContent(Content content) {
        Session session = sessionFactory.getCurrentSession();
        session.update(content);
    }

    @Override
    @Transactional
    public void deleteContent(int contentId, String userid) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Content.class);
        Content content = (Content) criteria.add(Restrictions.eq("id", contentId)).uniqueResult();
        content.getDefaultColumns().setModifier(userid);
        content.getDefaultColumns().setUseYn(false);
        session.update(content);
    }

    @Override
    @Transactional
    public List<Content> getBbsNoticeThumnailList(int bbsId) {
        return null;
    }
}
