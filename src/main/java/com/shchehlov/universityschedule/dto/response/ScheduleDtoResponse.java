package com.shchehlov.universityschedule.dto.response;

import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.model.Schedule;
import com.shchehlov.universityschedule.model.attributes.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDtoResponse {

    private Long id;

    private WeekDay weekDay;

    private Group group;

    public static ScheduleDtoResponse toDto(Schedule schedule) {
        return new ScheduleDtoResponse(
                schedule.getId(),
                schedule.getWeekDay(),
                schedule.getGroup());
    }
}
