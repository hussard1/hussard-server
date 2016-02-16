package com.hussard.web.base.bbs.repository;

import com.hussard.web.base.bbs.domain.BbsFile;
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
@Repository
public class FileRepositoryImpl implements FileRepository{

    final static String namespace = "com.hussard.web.base.bbs.repository.FileMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void saveFile(BbsFile bbsFile) {
        sqlSession.insert(namespace + ".saveFile", bbsFile);
    }

    @Override
    public List<BbsFile> findFileByContentId(int contentId) {
        return sqlSession.selectList(namespace + ".findFileByContentId", contentId);
    }

    @Override
    public BbsFile findFileByFileId(int fileId) {
        return sqlSession.selectOne(namespace + ".findFileByFileId", fileId);
    }

    @Override
    public void deleteFile(Content content, String userId) {

        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("userId", userId);

        Integer[] fileIds = content.getFileDelId();
        for(int i = 0; i < fileIds.length; i++){
            parameterMap.put("fileId", fileIds[i]);
            sqlSession.update(namespace + ".deleteFile", parameterMap);
        }
    }
}
