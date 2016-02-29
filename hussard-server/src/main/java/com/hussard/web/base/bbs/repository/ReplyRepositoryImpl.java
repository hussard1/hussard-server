package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.Reply;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by user on 2016-02-17.
 */
@Repository
public class ReplyRepositoryImpl implements ReplyRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveReply(Reply reply) {
        Session session = sessionFactory.getCurrentSession();
        session.save(reply);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Reply> findReplyList(int contentId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Reply.class);
        criteria.add(Restrictions.eq("content.id", contentId));
        return criteria.list();
    }

    @Override
    @Transactional
    public void deleteReply(Reply replyId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Reply.class);
        Reply reply= (Reply) criteria.add(Restrictions.eq("id", replyId)).uniqueResult();
        reply.getDefaultColumns().setUseYn(false);
        session.save(reply);
    }
}
