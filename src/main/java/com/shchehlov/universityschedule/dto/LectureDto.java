package com.shchehlov.universityschedule.dto;

import com.shchehlov.universityschedule.model.Classroom;
import com.shchehlov.universityschedule.model.Lecture;
import com.shchehlov.universityschedule.model.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.LECTURE_NAME_NOT_EMPTY;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.LECTURE_NAME_SIZE_CONSTRAINT;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto {

    private Long id;

    private Classroom classroom;

    private Schedule schedule;

    @NotEmpty(message = LECTURE_NAME_NOT_EMPTY)
    @Size(max = 255, message = LECTURE_NAME_SIZE_CONSTRAINT)
    private String name;

    public Lecture getItem() {
        return new Lecture(id, classroom, schedule, name);
    }
}
