package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.BbsFile;
import com.hussard.web.base.bbs.domain.Content;
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
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void saveFile(BbsFile bbsFile) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bbsFile);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<BbsFile> findFileByContentId(int contentId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(BbsFile.class);

        return  criteria.add(Restrictions.eq("content.id", contentId)).list();
    }

    @Override
    @Transactional
    public BbsFile findFileByFileId(int fileId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(BbsFile.class);

        return (BbsFile) criteria.add(Restrictions.eq("id", fileId)).uniqueResult();
    }

    @Override
    @Transactional
    public void deleteFile(Content content, String userId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(BbsFile.class);
    }
}
