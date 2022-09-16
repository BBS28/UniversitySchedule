package com.shchehlov.universityschedule.service;

import com.shchehlov.universityschedule.model.Group;

import java.util.List;

public interface GroupService {

    Group getById(Long id);

    Group save(Group group);

    void delete(Long id);

    void update(Group group);

    List<Group> getAll();

}
