package com.hussard.web.base.setup.repository;

import com.hussard.web.base.domain.Message;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Matthew on 2014-08-26.
 */
@Repository("messageRepository")
public class MessageRepositoryImpl implements MessageRepository {

    public static final int MESSAGE_LOAD_BATCH_SIZE = 1000;
    public static final String NAMESPACE = "com.hussard.web.base.setup.repository.MessageMapper.";

    @Autowired
    private SqlSession sqlSession;

    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    @Transactional
    public List<Message> getMessages() {
        List<Message> messages = sqlSession.selectList(NAMESPACE + "getMessages");

        return messages;
    }

    @Override
    public void save(List<Message> messages) {

       /* DefaultTransactionDefinition def = new DefaultTransactionDefinition();

        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = txManager.getTransaction(def);

        for (int i = 0; i < messages.size(); i++) {
            sqlSession.insert(NAMESPACE + "saveMessages",messages.get(i));
            if (i % MESSAGE_LOAD_BATCH_SIZE == 0) {
                sqlSession.flushStatements();
                sqlSession.clearCache();
            }
        }

        txManager.commit(status);
        sqlSession.close();*/
    }

    @Override
    @Transactional
    public void clear() {
      /*  sqlSession.delete(NAMESPACE + "deleteMessages");*/
    }

}
