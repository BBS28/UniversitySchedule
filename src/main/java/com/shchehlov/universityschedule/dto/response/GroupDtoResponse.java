package com.shchehlov.universityschedule.dto.response;

import com.shchehlov.universityschedule.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDtoResponse {

    private Long id;

    private String name;

    public static GroupDtoResponse toDto(Group group) {
        return new GroupDtoResponse(
                group.getId(),
                group.getName());
    }
}
