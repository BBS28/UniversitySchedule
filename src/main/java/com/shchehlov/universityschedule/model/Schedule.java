package com.shchehlov.universityschedule.model;

import com.shchehlov.universityschedule.model.attributes.WeekDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule", schema = "university_schedule")
public class Schedule {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "week_day")
    @Enumerated(value = EnumType.STRING)
    private WeekDay weekDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Schedule(WeekDay weekDay, Group group) {
        this.weekDay = weekDay;
        this.group = group;
    }
}
