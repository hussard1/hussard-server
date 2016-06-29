package com.hussard.web.base.auth.service;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.domain.SecuredResource;
import com.hussard.web.base.auth.repository.SecuredResourceRepository;
import com.hussard.web.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by hussard on 2016. 6. 28..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SecuredResourceServiceTest {

    @Autowired
    private SecuredResourceRepository securedResourceRepository;

    @Test
    public void getMetaDataSource() throws Exception {

        Map<RequestMatcher, Collection<ConfigAttribute>> resultMap = new LinkedHashMap<>();


        List<ConfigAttribute> configs = new LinkedList<>();

        List<SecuredResource> securedResources = securedResourceRepository.getSecuredResources();

        for(SecuredResource securedResource : securedResources){
            for(Authority authority : securedResource.getAuthorities()){
                configs.add(new SecurityConfig(authority.getName()));
            }
            resultMap.put(new AntPathRequestMatcher(securedResource.getPattern()), configs);
        }


        System.out.println("11111111" + resultMap);

    }

}