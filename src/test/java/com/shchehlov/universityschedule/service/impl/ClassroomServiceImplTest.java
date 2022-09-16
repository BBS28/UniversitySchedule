package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.repository.ClassroomRepository;
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
class ClassroomServiceImplTest {

    private Classroom classroom1;

    private Classroom classroom2;

    @InjectMocks
    private ClassroomServiceImpl classroomService;

    @Mock
    private ClassroomRepository classroomRepository;

    @BeforeEach
    void setUp() {
        classroom1 = new Classroom(1L, "101");
        classroom2 = new Classroom(2L, "202");
    }

    @Test
    void testGetById() {
        when(classroomRepository.findById(1L)).thenReturn(Optional.of(classroom1));
        when(classroomRepository.findById(15L)).thenReturn(Optional.empty());

        Classroom foundClassroom = classroomService.getById(1L);

        assertThat(foundClassroom).isNotNull().isEqualTo(classroom1);
        assertThrows(ResourceNotFoundException.class, () -> classroomService.getById(15L));

    }

    @Test
    void testGetAll() {
        List<Classroom> classroomList = new ArrayList<>();
        classroomList.add(classroom1);
        classroomList.add(classroom2);
        when(classroomRepository.findAll()).thenReturn(classroomList);

        List<Classroom> foundClassrooms = classroomService.getAll();

        assertThat(classroomList).isEqualTo(foundClassrooms).contains(classroom1);
    }

    @Test
    void testSave() {
        when(classroomRepository.save(classroom1)).thenReturn(classroom1);

        Classroom createdClassroom = classroomService.save(classroom1);

        assertThat(createdClassroom).isNotNull().isEqualTo(classroom1);
    }

    @Test
    void testDelete() {
        when(classroomRepository.findById(classroom1.getId())).thenReturn(Optional.of(classroom1));
        when(classroomRepository.findById(classroom2.getId())).thenReturn(Optional.empty());
        doNothing().when(classroomRepository).deleteById(classroom1.getId());

        classroomService.delete(classroom1.getId());

        assertThrows(ResourceNotFoundException.class, () -> classroomService.delete(2L));
        verify(classroomRepository).deleteById(classroom1.getId());
    }

    @Test
    void testUpdate() {
        when(classroomRepository.findById(classroom1.getId())).thenReturn(Optional.of(classroom1));
        when(classroomRepository.findById(classroom2.getId())).thenReturn(Optional.empty());
        when(classroomRepository.save(classroom1)).thenReturn(classroom1);

        classroomService.update(classroom1);

        verify(classroomRepository).save(classroom1);
        assertThrows(ResourceNotFoundException.class, () -> classroomService.delete(2L));
    }

}
