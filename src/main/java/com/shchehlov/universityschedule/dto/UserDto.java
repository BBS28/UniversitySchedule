package com.shchehlov.universityschedule.dto;

import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.EMAIL_SIZE_CONSTRAINT;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.EMAIL_VALID;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.FIRST_NAME_NOT_EMPTY;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.FIRST_NAME_SIZE_CONSTRAINT;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.LAST_NAME_NOT_EMPTY;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.LAST_NAME_SIZE_CONSTRAINT;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.PASSWORD_REGEXP;
import static com.shchehlov.universityschedule.constant.ValidationMessageConstant.PASSWORD_REGEXP_MESSAGE;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = FIRST_NAME_NOT_EMPTY)
    @Size(max = 100, message = FIRST_NAME_SIZE_CONSTRAINT)
    private String firstName;

    @NotEmpty(message = LAST_NAME_NOT_EMPTY)
    @Size(max = 100, message = LAST_NAME_SIZE_CONSTRAINT)
    private String lastName;

    @Email(message = EMAIL_VALID)
    @Size(max = 255, message = EMAIL_SIZE_CONSTRAINT)
    private String email;


    private Group group;

    public User getItem() {
        return new User(id, firstName, lastName, email, group);
    }
}
