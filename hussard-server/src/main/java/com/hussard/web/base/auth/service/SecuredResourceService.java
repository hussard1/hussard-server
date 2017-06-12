package com.hussard.web.base.auth.service;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.domain.SecuredResource;
import com.hussard.web.base.auth.repository.SecuredResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hussard on 2016. 6. 28..
 */
@Service
public class SecuredResourceService {

    @Autowired
    private SecuredResourceRepository securedResourceRepository;


    public List<SecuredResource> getSecuredResources() {
        return securedResourceRepository.getSecuredResources();
    }

    public Map<RequestMatcher, Collection<ConfigAttribute>> getMetaDataSource() {

        Map<RequestMatcher, Collection<ConfigAttribute>> resultMap = new LinkedHashMap<>();

        List<ConfigAttribute> configs = new LinkedList<>();

        List<SecuredResource> securedResources = securedResourceRepository.getSecuredResources();

        for(SecuredResource securedResource : securedResources){
            for(Authority authority : securedResource.getAuthorities()){
                configs.add(new SecurityConfig(authority.getName()));
            }
            resultMap.put(new AntPathRequestMatcher(securedResource.getPattern()), configs);
        }

        return resultMap;
    }
}
