package com.shchehlov.universityschedule.service.impl;

import com.shchehlov.universityschedule.exceptions.ResourceNotFoundException;
import com.shchehlov.universityschedule.model.Schedule;
import com.shchehlov.universityschedule.repository.ScheduleRepository;
import com.shchehlov.universityschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shchehlov.universityschedule.constant.ExceptionMessageConstant.SCHEDULE_ID_NOT_FOUND_FORMAT;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SCHEDULE_ID_NOT_FOUND_FORMAT, id)));
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        scheduleRepository.findById(schedule.getId())
                .ifPresentOrElse(s -> scheduleRepository.save(schedule), () -> {
                    throw new ResourceNotFoundException(String.format(SCHEDULE_ID_NOT_FOUND_FORMAT, schedule.getId()));
                });
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.findById(id)
                .ifPresentOrElse(s -> scheduleRepository.deleteById(id), () -> {
                    throw new ResourceNotFoundException(String.format(SCHEDULE_ID_NOT_FOUND_FORMAT, id));
                });
    }
}
