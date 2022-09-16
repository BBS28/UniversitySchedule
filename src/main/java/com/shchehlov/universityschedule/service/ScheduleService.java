package com.shchehlov.universityschedule.service;

import com.shchehlov.universityschedule.model.Schedule;

import java.util.List;

public interface ScheduleService {

    Schedule getById(Long id);

    List<Schedule> getAll();

    Schedule save(Schedule schedule);

    void update(Schedule schedule);

    void delete(Long id);

}
