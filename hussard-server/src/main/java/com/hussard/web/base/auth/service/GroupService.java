package com.hussard.web.base.auth.service;

import com.hussard.web.base.auth.domain.Group;
import com.hussard.web.base.auth.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hussard on 2016. 7. 8..
 */
@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group getGroup(String name) {
        return groupRepository.getGroup(name);
    }

    public List<Group> getGroups() {
        return groupRepository.getGroups();
    }

    public Group getGroup(long Id) {
        return groupRepository.getGroup(Id);
    }
}
