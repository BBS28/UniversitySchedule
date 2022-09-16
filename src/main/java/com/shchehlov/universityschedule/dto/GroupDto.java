package com.shchehlov.universityschedule.dto;

import com.shchehlov.universityschedule.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.GROUP_NAME_NOT_EMPTY;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.GROUP_NAME_SIZE_CONSTRAINT;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private Long id;

    @NotEmpty(message = GROUP_NAME_NOT_EMPTY)
    @Size(max = 255, message = GROUP_NAME_SIZE_CONSTRAINT)
    private String name;

    public Group getItem() {
        return new Group(id, name);
    }

}
