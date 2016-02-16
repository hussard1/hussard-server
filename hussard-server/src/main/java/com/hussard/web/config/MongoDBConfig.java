package com.hussard.web.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mapping.model.CamelCaseAbbreviatingFieldNamingStrategy;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matthew on 2015-06-09.
 */
@Configuration
@EnableMongoRepositories
@PropertySource("classpath:database.properties")
public class MongoDBConfig {

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private int port;

    @Value("${mongo.db}")
    private String databaseName;

    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public GridFsOperations gridFsOperations() throws ClassNotFoundException, UnknownHostException {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoClient client = new MongoClient(host, port);
        return new SimpleMongoDbFactory(client, databaseName);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() throws ClassNotFoundException, UnknownHostException {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext());
        converter.setCustomConversions(customConversions());

        return converter;
    }

    @Bean
    public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
        MongoMappingContext mappingContext = mappingContext();
        mappingContext.setInitialEntitySet(getInitialEntitySet());
        mappingContext.setSimpleTypeHolder(customConversions().getSimpleTypeHolder());

        if (abbreviateFieldNames()) {
            mappingContext.setFieldNamingStrategy(new CamelCaseAbbreviatingFieldNamingStrategy());
        }

        return mappingContext;
    }

    @Bean
    public CustomConversions customConversions() {
        return new CustomConversions(Collections.emptyList());
    }

    @Bean
    public MongoMappingContext mappingContext() {
        return new MongoMappingContext();
    }

    protected String getMappingBasePackage() {
        Package mappingBasePackage = getClass().getPackage();
        return mappingBasePackage == null ? null : mappingBasePackage.getName();
    }

    protected Set<Class<?>> getInitialEntitySet() throws ClassNotFoundException {
        String basePackage = getMappingBasePackage();
        Set<Class<?>> initialEntitySet = new HashSet<>();

        if (StringUtils.hasText(basePackage)) {
            ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(false);
            componentProvider.addIncludeFilter(new AnnotationTypeFilter(Document.class));
            componentProvider.addIncludeFilter(new AnnotationTypeFilter(Persistent.class));

            for (BeanDefinition candidate : componentProvider.findCandidateComponents(basePackage)) {
                initialEntitySet.add(ClassUtils.forName(candidate.getBeanClassName(), AbstractMongoConfiguration.class.getClassLoader()));
            }
        }

        return initialEntitySet;
    }

    protected boolean abbreviateFieldNames() {
        return false;
    }
}
