package com.hussard.web.config;

import com.google.common.collect.ImmutableMap;
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
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;
import java.util.Properties;
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

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.connection.release_mode}")
    private String releaseMode;


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

    /* myBatis Configuration */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("sqlMap-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/hussard/web*//***/*//*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(datasource());
//        return transactionManager;
//    }

    /* Hibernate Configuration */
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(datasource());
        sessionFactory.setPackagesToScan(new String[]{
                "com.hussard.web"
        });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws Exception {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }


    private Properties hibernateProperties() {
        Map<String, String> map = new ImmutableMap.Builder<String, String>()
                .put("hibernate.hbm2ddl.auto", hbm2ddl)
                .put("hibernate.dialect", dialect)
                .put("hibernate.globally_quoted_identifiers", "true")
                .put("hibernate.show_sql", showSql)
                .put("hibernate.connection.release_mode", releaseMode)
                .build();

        Properties properties = new Properties();
        properties.putAll(map);

        return properties;
    }
}
