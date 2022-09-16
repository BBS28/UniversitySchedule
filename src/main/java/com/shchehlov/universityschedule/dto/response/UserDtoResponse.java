package com.shchehlov.universityschedule.dto.response;

import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Group group;

    public static UserDtoResponse toDto(User user) {
        return new UserDtoResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGroup());
    }
}
