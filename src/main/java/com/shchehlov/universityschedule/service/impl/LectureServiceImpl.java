package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.repository.LectureRepository;
import com.shchehlov.universityschedule.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shchehlov.universityschedule.constant.ExceptionMessageConstant.LECTURE_ID_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    @Override
    public Lecture getById(Long id) {
        return lectureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(LECTURE_ID_NOT_FOUND_FORMAT, id)));
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public void update(Lecture lecture) {
        lectureRepository.findById(lecture.getId())
                .ifPresentOrElse(l -> lectureRepository.save(lecture), () -> {
                    throw new ResourceNotFoundException(String.format(LECTURE_ID_NOT_FOUND_FORMAT, lecture.getId()));
                });
    }

    @Override
    public void delete(Long id) {
        lectureRepository.findById(id)
                .ifPresentOrElse(l -> lectureRepository.deleteById(id), () -> {
                    throw new ResourceNotFoundException(String.format(LECTURE_ID_NOT_FOUND_FORMAT, id));
                });
    }
}
