package com.shchehlov.universityschedule.service;

import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.User;
import com.shchehlov.universityschedule.model.attributes.WeekDay;

import java.util.List;

public interface UserService {

    User getById(Long id);

    User save(User user);

    void delete(Long id);

    void update(User user);

    List<User> getAll();

    List<Lecture> getLecturesScheduleForWeekDay(Long userId, WeekDay weekDay);
}
