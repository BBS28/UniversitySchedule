package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.repository.ClassroomRepository;
import com.shchehlov.universityschedule.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shchehlov.universityschedule.constant.ExceptionMessageConstant.CLASSROOM_ID_NOT_FOUND_FORMAT;
import static com.shchehlov.universityschedule.constant.ExceptionMessageConstant.GROUP_ID_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Override
    public Classroom getById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CLASSROOM_ID_NOT_FOUND_FORMAT, id)));
    }

    @Override
    public List<Classroom> getAll() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    @Override
    public void delete(Long id) {
        classroomRepository.findById(id)
                .ifPresentOrElse(g -> classroomRepository.deleteById(id), () -> {
                    throw new ResourceNotFoundException(String.format(GROUP_ID_NOT_FOUND_FORMAT, id));
                });
    }

    @Override
    public void update(Classroom classroom) {
        classroomRepository.findById(classroom.getId())
                .ifPresentOrElse(g -> classroomRepository.save(classroom), () -> {
                    throw new ResourceNotFoundException(String.format(GROUP_ID_NOT_FOUND_FORMAT, classroom.getId()));
                });
    }
}
