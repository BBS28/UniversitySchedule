package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.Schedule;
import com.shchehlov.universityschedule.repository.LectureRepository;
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
class LectureServiceImplTest {

    private Lecture lecture1;

    private Lecture lecture2;

    @InjectMocks
    private LectureServiceImpl lectureService;

    @Mock
    private LectureRepository lectureRepository;

    @BeforeEach
    void Setup() {
        lecture1 = new Lecture(1L, new Classroom(), new Schedule(), "Maths");
        lecture2 = new Lecture(2L, new Classroom(), new Schedule(), "Physics");
    }

    @Test
    void testGetById() {
        when(lectureRepository.findById(1L)).thenReturn(Optional.of(lecture1));
        when(lectureRepository.findById(15L)).thenReturn(Optional.empty());

        Lecture foundLecture = lectureService.getById(1L);

        assertThat(foundLecture).isNotNull().isEqualTo(lecture1);
        assertThrows(ResourceNotFoundException.class, () -> lectureService.getById(15L));

    }

    @Test
    void testGetAll() {
        List<Lecture> lectureList = new ArrayList<>();
        lectureList.add(lecture1);
        lectureList.add(lecture2);
        when(lectureRepository.findAll()).thenReturn(lectureList);

        List<Lecture> foundLectures = lectureService.getAll();

        assertThat(lectureList).isEqualTo(foundLectures).contains(lecture1);
    }

    @Test
    void testSave() {
        when(lectureRepository.save(lecture1)).thenReturn(lecture1);

        Lecture createdLecture = lectureService.save(lecture1);

        assertThat(createdLecture).isNotNull().isEqualTo(lecture1);
    }

    @Test
    void testDelete() {
        when(lectureRepository.findById(lecture1.getId())).thenReturn(Optional.of(lecture1));
        when(lectureRepository.findById(lecture2.getId())).thenReturn(Optional.empty());
        doNothing().when(lectureRepository).deleteById(lecture1.getId());

        lectureService.delete(lecture1.getId());

        assertThrows(ResourceNotFoundException.class, () -> lectureService.delete(2L));
        verify(lectureRepository).deleteById(lecture1.getId());
    }

    @Test
    void testUpdate() {
        when(lectureRepository.findById(lecture1.getId())).thenReturn(Optional.of(lecture1));
        when(lectureRepository.findById(lecture2.getId())).thenReturn(Optional.empty());
        when(lectureRepository.save(lecture1)).thenReturn(lecture1);

        lectureService.update(lecture1);

        verify(lectureRepository).save(lecture1);
        assertThrows(ResourceNotFoundException.class, () -> lectureService.delete(2L));

    }



}
