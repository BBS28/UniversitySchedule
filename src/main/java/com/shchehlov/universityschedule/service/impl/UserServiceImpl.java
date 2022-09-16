package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.IllegalUserToSaveException;
import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.User;
import com.shchehlov.universityschedule.model.attributes.WeekDay;
import com.shchehlov.universityschedule.repository.LectureRepository;
import com.shchehlov.universityschedule.repository.UserRepository;
import com.shchehlov.universityschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shchehlov.universityschedule.constant.ExceptionMessageConstant.USER_ID_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LectureRepository lectureRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_ID_NOT_FOUND_FORMAT, id)));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalUserToSaveException(String.format("User with email %s already exists", user.getEmail()));
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id)
                .ifPresentOrElse(user -> userRepository.deleteById(id), () -> {
                    throw new ResourceNotFoundException(String.format(USER_ID_NOT_FOUND_FORMAT, id));
                });
    }

    @Override
    public void update(User user) {
        userRepository.findById(user.getId())
                .ifPresentOrElse(u -> userRepository.save(user), () -> {
            throw new ResourceNotFoundException(String.format(USER_ID_NOT_FOUND_FORMAT, user.getId()));
        });
    }

    @Override
    public List<Lecture> getLecturesScheduleForWeekDay(Long userId, WeekDay weekDay) {
        return lectureRepository.findAllLecturesById(userId, weekDay.toString());
    }
}
