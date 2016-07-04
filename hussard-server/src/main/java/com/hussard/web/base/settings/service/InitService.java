package com.hussard.web.base.settings.service;

import org.springframework.stereotype.Service;

/**
 * Created by hussard on 2016. 6. 15..
 */
public interface InitService {

    void createDefaultSecuredResource();

    void createDefaultAuthority();

    void createDefaultGroup();

    void createDefaultUser();

    void createAnonymousSecuredResource();
}
