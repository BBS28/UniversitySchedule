package com.shchehlov.universityschedule.dto.response;

import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureDtoResponse {

    private Long id;

    private Classroom classroom;

    private Schedule schedule;

    private String name;

    public static LectureDtoResponse toDto(Lecture lecture) {
        return new LectureDtoResponse(
                lecture.getId(),
                lecture.getClassroom(),
                lecture.getSchedule(),
                lecture.getName());
    }
}
