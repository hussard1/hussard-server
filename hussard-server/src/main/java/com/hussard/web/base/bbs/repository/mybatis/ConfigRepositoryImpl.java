package com.hussard.web.base.bbs.repository.mybatis;

import com.hussard.web.base.bbs.domain.Config;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 2015-07-13.
 */
@Repository("MybatisConfigRepositoryImpl")
public class ConfigRepositoryImpl implements ConfigRepository {

    final static String namespace = "com.hussard.web.base.bbs.repository.ConfigMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<Config> findConfigList() {
        return sqlSession.selectList(namespace+".findConfigList");
    }

    @Override
    public Config findConfigByBbsId(int bbsId) {
        return sqlSession.selectOne(namespace+".findConfigByBbsId", bbsId);
    }

    @Override
    public void saveConfig(Config config) {
        sqlSession.insert(namespace + ".saveConfig", config);
    }

    @Override
    public void updateConfig(Config config) {
        sqlSession.update(namespace + ".updateConfig", config);
    }
}
