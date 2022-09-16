package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.IllegalUserToSaveException;
import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.User;
import com.shchehlov.universityschedule.model.attributes.WeekDay;
import com.shchehlov.universityschedule.repository.LectureRepository;
import com.shchehlov.universityschedule.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private User user1;
    private User user2;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LectureRepository lectureRepository;

    @BeforeEach
    void setUp() {
        user1 = new User(1L,
                "name",
                "Lastname",
                "email@email.exem",
                new Group());

        user2 = new User(2L,
                "name2",
                "Lastname2",
                "email2@email.exem",
                new Group());
    }

    @Test
    void testGetById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(15L)).thenReturn(Optional.empty());

        User foundUser = userService.getById(1L);

        assertThat(foundUser).isNotNull().isEqualTo(user1);
        assertThrows(ResourceNotFoundException.class, () -> userService.getById(15L));
    }

    @Test
    void testGetAll() {
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> foundUsers = userService.getAll();

        assertThat(userList).isEqualTo(foundUsers).contains(user1);
    }

    @Test
    void testSave() {
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.findByEmail(user1.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(user2.getEmail())).thenReturn(Optional.of(user2));

        User createdUser = userService.save(user1);

        assertThat(createdUser).isNotNull().isEqualTo(user1);
        assertThrows(IllegalUserToSaveException.class, () -> userService.save(user2));
    }

    @Test
    void testDelete() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(userRepository.findById(user2.getId())).thenReturn(Optional.empty());
        doNothing().when(userRepository).deleteById(user1.getId());

        userService.delete(user1.getId());

        assertThrows(ResourceNotFoundException.class, () -> userService.delete(2L));
        verify(userRepository).deleteById(user1.getId());

    }

    @Test
    void testUpdate() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(userRepository.findById(user2.getId())).thenReturn(Optional.empty());
        when(userRepository.save(user1)).thenReturn(user1);

        userService.update(user1);

        verify(userRepository).save(user1);
        assertThrows(ResourceNotFoundException.class, () -> userService.delete(2L));

    }

    @Test
    void testGetLecturesScheduleForWeekDay() {
        List<Lecture> lectures = new ArrayList<>();
        when(lectureRepository.findAllLecturesById(anyLong(), anyString())).thenReturn(lectures);

        List<Lecture> foundLectures = userService.getLecturesScheduleForWeekDay(10L, WeekDay.MONDAY);

        assertThat(foundLectures).isEqualTo(lectures);
    }


}
