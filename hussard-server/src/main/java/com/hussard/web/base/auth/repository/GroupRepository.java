package com.hussard.web.base.auth.repository;

import com.hussard.web.base.auth.domain.Group;

import java.util.List;

/**
 * Created by hussard on 2016. 6. 15..
 */
public interface GroupRepository {

    Group getGroup(String name);

    void save(Group group);

    List<Group> getGroups();

    Group getGroup(long id);
}
