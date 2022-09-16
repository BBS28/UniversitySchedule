package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.model.Schedule;
import com.shchehlov.universityschedule.model.attributes.WeekDay;
import com.shchehlov.universityschedule.repository.ScheduleRepository;
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
class ScheduleServiceImplTest {

    private Schedule schedule1;

    private Schedule schedule2;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    void setUp() {
        schedule1 = new Schedule(1L, WeekDay.MONDAY, new Group());
        schedule2 = new Schedule(2L, WeekDay.FRIDAY, new Group());
    }

    @Test
    void testGetById() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule1));
        when(scheduleRepository.findById(15L)).thenReturn(Optional.empty());

        Schedule foundSchedule = scheduleService.getById(1L);

        assertThat(foundSchedule).isNotNull().isEqualTo(schedule1);
        assertThrows(ResourceNotFoundException.class, () -> scheduleService.getById(15L));

    }

    @Test
    void testGetAll() {
        List<Schedule> classroomList = new ArrayList<>();
        classroomList.add(schedule1);
        classroomList.add(schedule2);
        when(scheduleRepository.findAll()).thenReturn(classroomList);

        List<Schedule> foundClassrooms = scheduleService.getAll();

        assertThat(classroomList).isEqualTo(foundClassrooms).contains(schedule1);
    }

    @Test
    void testSave() {
        when(scheduleRepository.save(schedule1)).thenReturn(schedule1);

        Schedule createdClassroom = scheduleService.save(schedule1);

        assertThat(createdClassroom).isNotNull().isEqualTo(schedule1);
    }

    @Test
    void testDelete() {
        when(scheduleRepository.findById(schedule1.getId())).thenReturn(Optional.of(schedule1));
        when(scheduleRepository.findById(schedule2.getId())).thenReturn(Optional.empty());
        doNothing().when(scheduleRepository).deleteById(schedule1.getId());

        scheduleService.delete(schedule1.getId());

        assertThrows(ResourceNotFoundException.class, () -> scheduleService.delete(2L));
        verify(scheduleRepository).deleteById(schedule1.getId());
    }

    @Test
    void testUpdate() {
        when(scheduleRepository.findById(schedule1.getId())).thenReturn(Optional.of(schedule1));
        when(scheduleRepository.findById(schedule2.getId())).thenReturn(Optional.empty());
        when(scheduleRepository.save(schedule1)).thenReturn(schedule1);

        scheduleService.update(schedule1);

        verify(scheduleRepository).save(schedule1);
        assertThrows(ResourceNotFoundException.class, () -> scheduleService.delete(2L));

    }


}
