package com.shchehlov.universityschedule.dto.response;

import com.shchehlov.universityschedule.model.Classroom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDtoResponse {

    private Long id;

    private String name;

    public static ClassroomDtoResponse toDto(Classroom classroom) {
        return new ClassroomDtoResponse(
                classroom.getId(),
                classroom.getName());
    }
}

