package com.hussard.web.base.settings.service;

import com.hussard.web.base.auth.domain.Authority;
import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.auth.domain.SecuredResource;
import com.hussard.web.base.auth.repository.AuthorityRepository;
import com.hussard.web.base.auth.repository.GroupRepository;
import com.hussard.web.base.auth.repository.SecuredResourceRepository;
import com.hussard.web.base.user.domain.Country;
import com.hussard.web.base.user.domain.Language;
import com.hussard.web.base.user.domain.User;
import com.hussard.web.base.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hussard on 2016. 6. 15..
 */

@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private SecuredResourceRepository securedResourceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createDefaultSecuredResource() {
        SecuredResource adminSecuredResource = securedResourceRepository.getSecuredResource("ADMIN_RESOURCE");
        if(adminSecuredResource != null){
            return;
        }

        adminSecuredResource = new SecuredResource();
        adminSecuredResource.setName("ADMIN_RESOURCE");
        adminSecuredResource.setPattern("admin/**");
        adminSecuredResource.setSortOrder(0);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getAuthority("ADMIN_AUTHORITY"));
        adminSecuredResource.setAuthorities(authorities);

        securedResourceRepository.save(adminSecuredResource);
    }

    @Override
    public void createDefaultAuthority() {
        Authority adminAuthority = authorityRepository.getAuthority("ADMIN_AUTHORITY");

        if(adminAuthority != null){
            return;
        }

        adminAuthority = new Authority();
        adminAuthority.setName("ADMIN_AUTHORITY");

        authorityRepository.save(adminAuthority);

    }

    @Override
    public void createDefaultGroup() {
        Group adminGroup = groupRepository.getGroup("ADMIN_GROUP");
        if (adminGroup != null) {
            return;
        }
        // admin
        adminGroup = new Group();
        adminGroup.setName("ADMIN_GROUP");
        adminGroup.setDesc("Administration");

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getAuthority("ADMIN_AUTHORITY"));
        adminGroup.setAuthorities(authorities);

        groupRepository.save(adminGroup);

    }

    @Override
    public void createDefaultUser() {
        User user = userRepository.getUser("admin");
        if (user != null) {
            return;
        }

        user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("1"));
        user.setFullname("Administrator");
        user.setEmail("convergence@motrex.co.kr");
        user.setLanguage(Language.ko);
        user.setCountry(Country.KR);


        Set<Group> groups = new HashSet<>();
        groups.add(groupRepository.getGroup("ADMIN_GROUP"));
        user.setGroups(groups);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getAuthority("ADMIN_AUTHORITY"));
        user.setAuthorities(authorities);

//        user.setTimezone(Timezone.en);
//        user.setBase(getDefaultBase());

//        user.setGroups(groups);
//        user.setVendor(vendorRepository.getVendor("TP041"));

        userRepository.save(user);

    }
}
