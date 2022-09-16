package com.shchehlov.universityschedule.dto;

import com.shchehlov.universityschedule.model.Group;
import com.shchehlov.universityschedule.model.Schedule;
import com.shchehlov.universityschedule.model.attributes.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private Long id;

    private WeekDay weekDay;

    private Group group;

    public Schedule getItem() {
        return new Schedule(id, weekDay, group);
    }
}
