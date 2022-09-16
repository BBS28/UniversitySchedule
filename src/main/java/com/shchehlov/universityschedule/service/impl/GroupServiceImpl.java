package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.repository.GroupRepository;
import com.shchehlov.universityschedule.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shchehlov.universityschedule.constant.ExceptionMessageConstant.GROUP_ID_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public Group getById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(GROUP_ID_NOT_FOUND_FORMAT, id)));
    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void delete(Long id) {
        groupRepository.findById(id)
                .ifPresentOrElse(g -> groupRepository.deleteById(id), () -> {
                    throw new ResourceNotFoundException(String.format(GROUP_ID_NOT_FOUND_FORMAT, id));
                });
    }

    @Override
    public void update(Group group) {
        groupRepository.findById(group.getId())
                .ifPresentOrElse(g -> groupRepository.save(group), () -> {
                    throw new ResourceNotFoundException(String.format(GROUP_ID_NOT_FOUND_FORMAT, group.getId()));
                });
    }
}
