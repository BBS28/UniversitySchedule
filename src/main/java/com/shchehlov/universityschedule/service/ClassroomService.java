package com.shchehlov.universityschedule.service;

import com.shchehlov.universityschedule.model.Classroom;

import java.util.List;

public interface ClassroomService {

    Classroom getById(Long id);

    Classroom save(Classroom classroom);

    void delete(Long id);

    void update(Classroom classroom);

    List<Classroom> getAll();
}
