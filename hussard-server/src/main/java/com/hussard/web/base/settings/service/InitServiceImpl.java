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
        SecuredResource userSecuredResource = securedResourceRepository.getSecuredResource("USER_RESOURCE");
        if(adminSecuredResource != null || userSecuredResource != null){
            return;
        }

        adminSecuredResource = new SecuredResource();
        adminSecuredResource.setName("ADMIN_RESOURCE");
        adminSecuredResource.setPattern("/admin/**");
        adminSecuredResource.setSortOrder(0);

        Set<Authority> adminAuthorities = new HashSet<>();
        adminAuthorities.add(authorityRepository.getAuthority("ADMIN_AUTHORITY"));
        adminSecuredResource.setAuthorities(adminAuthorities);

        securedResourceRepository.save(adminSecuredResource);


        userSecuredResource = new SecuredResource();
        userSecuredResource.setName("USER_RESOURCE");
        userSecuredResource.setPattern("/**");
        userSecuredResource.setSortOrder(9999);

        Set<Authority> userAuthorities = new HashSet<>();
        userAuthorities.add(authorityRepository.getAuthority("USER_AUTHORITY"));
        userSecuredResource.setAuthorities(userAuthorities);

        securedResourceRepository.save(userSecuredResource);
    }


    @Override
    public void createAnonymousSecuredResource() {
        SecuredResource securedResource = securedResourceRepository.getSecuredResource("ANONYMOUS");

        if(securedResource != null){
            return;
        }

        securedResource = new SecuredResource();
        securedResource.setName("ANONYMOUS");
        securedResource.setPattern("/auth/**");
        securedResource.setSortOrder(1);

        securedResourceRepository.save(securedResource);
    }

    @Override
    public void createDefaultAuthority() {
        Authority adminAuthority = authorityRepository.getAuthority("ADMIN_AUTHORITY");
        Authority userAuthority = authorityRepository.getAuthority("USER_AUTHORITY");

        if(adminAuthority != null && userAuthority != null){
            return;
        }

        adminAuthority = new Authority();
        adminAuthority.setName("ADMIN_AUTHORITY");

        authorityRepository.save(adminAuthority);

        userAuthority = new Authority();
        userAuthority.setName("USER_AUTHORITY");
        authorityRepository.save(userAuthority);
    }

    @Override
    public void createDefaultGroup() {
        Group adminGroup = groupRepository.getGroup("ADMIN_GROUP");
        Group userGroup = groupRepository.getGroup("USER_GROUP");

        if (adminGroup != null || userGroup != null) {
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

        //User
        userGroup = new Group();
        userGroup.setName("USER_GROUP");
        userGroup.setDesc("user group");

        Set<Authority> userAuthorities = new HashSet<>();
        userAuthorities.add(authorityRepository.getAuthority("USER_AUTHORITY"));
        adminGroup.setAuthorities(userAuthorities);

        groupRepository.save(userGroup);

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
        user.setFirstName("Administrator");
        user.setLastName("Admin");
        user.setEmail("convergence@motrex.co.kr");
        user.setLanguage(Language.ko);
        user.setCountry(Country.KR);


        Set<Group> groups = new HashSet<>();
        groups.add(groupRepository.getGroup("ADMIN_GROUP"));
        groups.add(groupRepository.getGroup("USER_GROUP"));
        user.setGroups(groups);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authorityRepository.getAuthority("ADMIN_AUTHORITY"));
        authorities.add(authorityRepository.getAuthority("USER_AUTHORITY"));
        user.setAuthorities(authorities);

//        user.setTimezone(Timezone.en);
//        user.setBase(getDefaultBase());

//        user.setGroups(groups);
//        user.setVendor(vendorRepository.getVendor("TP041"));

        userRepository.save(user);
    }

}
