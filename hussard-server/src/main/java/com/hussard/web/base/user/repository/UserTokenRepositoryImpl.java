package com.hussard.web.base.user.repository;

import com.hussard.web.base.user.domain.Token;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew on 2015-06-09.
 */
@Repository("userTokenRepository")
public class UserTokenRepositoryImpl implements PersistentTokenRepository {

    private static final String NAMESPACE = "com.hussard.web.base.user.repository.UserTokenMapper.";

    @Autowired
    private SqlSession sqlSession;

    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        sqlSession.insert(NAMESPACE + "createNewToken", token);
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("series", series);
        parameter.put("tokenValue", tokenValue);
        parameter.put("lastUsed", lastUsed);

        sqlSession.insert(NAMESPACE + "updateToken", parameter);
    }

    @Override
    @Transactional
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        Token token = getTokenBySeries(seriesId);

        return new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getLastUsed());
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("username", username);

        sqlSession.delete(NAMESPACE + "removeUserToken", parameter);
    }

    @Transactional
    public Token getTokenBySeries(String series) {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("series", series);

        return (Token) sqlSession.selectOne(NAMESPACE + "getTokenBySeries", parameter);
    }
}
