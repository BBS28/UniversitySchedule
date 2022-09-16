package com.shchehlov.universityschedule.service;

import com.shchehlov.universityschedule.model.Lecture;

import java.util.List;

public interface LectureService {

    Lecture getById(Long id);

    List<Lecture> getAll();

    Lecture save(Lecture lecture);

    void update(Lecture lecture);

    void delete(Long id);


}
