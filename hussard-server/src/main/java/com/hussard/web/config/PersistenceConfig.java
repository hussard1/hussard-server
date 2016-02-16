package com.hussard.web.config;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

/**
 * Created by Matthew on 2015-06-09.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class PersistenceConfig {

    @Value("${db.driverClass}")
    private String driverClass;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    public BoneCPConfig datasourceConfig() {
        BoneCPConfig config = new BoneCPConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setIdleMaxAge(10, TimeUnit.MINUTES);
        config.setIdleConnectionTestPeriod(60, TimeUnit.MINUTES);
        config.setPartitionCount(3);
        config.setAcquireIncrement(10);
        config.setMaxConnectionsPerPartition(1);
        config.setMinConnectionsPerPartition(1);
        config.setStatementsCacheSize(50);

        return config;
    }

    @Bean
    public BoneCPDataSource datasource() {
        BoneCPDataSource datasource = new BoneCPDataSource(datasourceConfig());
        datasource.setDriverClass(driverClass);

        return datasource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(datasource());
        return transactionManager;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("sqlMap-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/hussard/web/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
