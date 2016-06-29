package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.SecuredResource;

import java.util.List;

/**
 * Created by hussard on 2016. 6. 28..
 */
public interface SecuredResourceRepository {

    SecuredResource getSecuredResource(String name);

    List<SecuredResource> getSecuredResources();

    void save(SecuredResource securedResource);

}
