package com.hussard.web.base.bbs.repository.mybatis;

import com.hussard.web.base.bbs.domain.Content;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2015-07-13.
 */
@Repository("MybatisContentRepositoryImpl")
public class ContentRepositoryImpl implements ContentRepository {

    final static String namespace = "com.hussard.web.base.bbs.repository.ContentMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Content> findListBySearchMode(int bbsId, int pageNum, int searchMode, String searchContent, int perPage) {

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("bbsId", bbsId);
        parameterMap.put("searchMode", searchMode);
        parameterMap.put("searchContent", searchContent);
        parameterMap.put("fromLimit", ((pageNum-1)*perPage));
        parameterMap.put("perPage", perPage);

        return sqlSession.selectList(namespace + ".findListBySearchMode", parameterMap);
    }

    @Override
    public int findCountByBbsId(int bbsId, int searchMode, String searchContent) {

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("bbsId", bbsId);
        parameterMap.put("searchMode", searchMode);
        parameterMap.put("searchContent", searchContent);

        return sqlSession.selectOne(namespace + ".findCountByBbsId", parameterMap);
    }

    @Override
    public void saveContent(Content content) {
        sqlSession.insert(namespace + ".saveContent", content);
    }

    @Override
    public Content findContentByContentId(int contentId) {
        return sqlSession.selectOne(namespace + ".findContentByContentId", contentId);
    }

    @Override
    public void updateContent(Content content) {
        sqlSession.update(namespace + ".updateContent", content);
    }

    @Override
    public void updateViewCnt(int contentId) {
        sqlSession.update(namespace + ".updateViewCnt", contentId);
    }

    @Override
    public void deleteContent(int contentId, String userid) {

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("contentId", contentId);
        parameterMap.put("userId", userid);

        sqlSession.update(namespace + ".deleteContent", parameterMap);
    }

    @Override
    public List<Content> getBbsNoticeThumnailList(int bbsId) {
        return sqlSession.selectList(namespace + ".getBbsNoticeThumnailList" , bbsId);
    }
}
