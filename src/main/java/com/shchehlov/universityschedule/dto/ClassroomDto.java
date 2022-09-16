package com.shchehlov.universityschedule.dto;

import com.shchehlov.universityschedule.model.Classroom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.CLASSROOM_NAME_NOT_EMPTY;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.CLASSROOM_NAME_SIZE_CONSTRAINT;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDto {

    private Long id;
    @NotEmpty(message = CLASSROOM_NAME_NOT_EMPTY)
    @Size(max = 100, message = CLASSROOM_NAME_SIZE_CONSTRAINT)
    private String name;

    public Classroom getItem() {
        return new Classroom(id, name);
    }
}
