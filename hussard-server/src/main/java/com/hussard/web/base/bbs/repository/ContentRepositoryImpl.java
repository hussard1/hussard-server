package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.Content;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public List<Content> findListBySearchMode(int bbsId, int pageNum, int perPage, int searchMode, String searchContent) {
        return null;
    }

    @Override
    @Transactional
    public long findCountByBbsId(int bbsId, int searchMode, String searchContent) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Content.class);

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
    public Content findContentByContentId(int contentId) {
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
    public void updateViewCnt(int contentId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Content.class);
        Content content = (Content) criteria.add(Restrictions.eq("id", contentId)).uniqueResult();
        content.setContentViewCnt(content.getContentViewCnt() + 1);
        content.setContentViewCnt(contentId);
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
