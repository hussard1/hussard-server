package com.hussard.web.base.bbs.repository.mybatis;

import com.hussard.web.base.bbs.domain.Reply;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2015-07-13.
 */
@Repository("MybatisReplyRepositoryImpl")
public class ReplyRepositoryImpl implements ReplyRepository {

    final static String namespace = "com.hussard.web.base.bbs.repository.ReplyMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void saveReply(Reply reply) {
        sqlSession.insert(namespace + ".saveReply", reply);
    }

    @Override
    public List<Reply> findReplyList(int contentId) {
        return sqlSession.selectList(namespace + ".findReplyList", contentId);
    }

    @Override
    public void deleteReply(Reply replyId) {
        sqlSession.update(namespace + ".deleteReply", replyId);
    }
}
