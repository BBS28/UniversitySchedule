package com.shchehlov.universityschedule.constant;

public class QueryConstant {
    private QueryConstant() {
    }

    public static final String SELECT_LECTURES_BY_USER_AND_WEEK_DAY = "SELECT l.id, l.name, l.classroom_id, l.schedule_id \n" +
            "FROM university_schedule.lectures l \n" +
            "LEFT JOIN university_schedule.schedule \n" +
            "ON l.schedule_id = schedule.id \n" +
            "LEFT JOIN university_schedule.groups \n" +
            "ON schedule.group_id = groups.id \n" +
            "LEFT JOIN university_schedule.users \n" +
            "ON users.group_id = groups.id \n" +
            "WHERE users.id = ?1 AND schedule.week_day = ?2";
}
