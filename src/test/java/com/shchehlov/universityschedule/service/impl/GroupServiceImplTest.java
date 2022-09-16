package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {

    private Group group1;

    private Group group2;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Mock
    private GroupRepository groupRepository;

    @BeforeEach
    void setUp() {
        group1 = new Group(1L, "101");
        group2 = new Group(2L, "202");
    }

    @Test
    void testGetById() {
        when(groupRepository.findById(1L)).thenReturn(Optional.of(group1));
        when(groupRepository.findById(15L)).thenReturn(Optional.empty());

        Group foundGroup = groupService.getById(1L);

        assertThat(foundGroup).isNotNull().isEqualTo(group1);
        assertThrows(ResourceNotFoundException.class, () -> groupService.getById(15L));

    }

    @Test
    void testGetAll() {
        List<Group> groupList = new ArrayList<>();
        groupList.add(group1);
        groupList.add(group2);
        when(groupRepository.findAll()).thenReturn(groupList);

        List<Group> foundClassrooms = groupService.getAll();

        assertThat(groupList).isEqualTo(foundClassrooms).contains(group1);
    }

    @Test
    void testSave() {
        when(groupRepository.save(group1)).thenReturn(group1);

        Group createdGroup = groupService.save(group1);

        assertThat(createdGroup).isNotNull().isEqualTo(group1);
    }

    @Test
    void testDelete() {
        when(groupRepository.findById(group1.getId())).thenReturn(Optional.of(group1));
        when(groupRepository.findById(group2.getId())).thenReturn(Optional.empty());
        doNothing().when(groupRepository).deleteById(group1.getId());

        groupService.delete(group1.getId());

        assertThrows(ResourceNotFoundException.class, () -> groupService.delete(2L));
        verify(groupRepository).deleteById(group1.getId());
    }

    @Test
    void testUpdate() {
        when(groupRepository.findById(group1.getId())).thenReturn(Optional.of(group1));
        when(groupRepository.findById(group2.getId())).thenReturn(Optional.empty());
        when(groupRepository.save(group1)).thenReturn(group1);

        groupService.update(group1);

        verify(groupRepository).save(group1);
        assertThrows(ResourceNotFoundException.class, () -> groupService.delete(2L));
    }
}
