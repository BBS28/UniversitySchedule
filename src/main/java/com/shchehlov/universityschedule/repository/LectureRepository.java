package com.shchehlov.universityschedule.repository;

import com.shchehlov.universityschedule.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shchehlov.universityschedule.constant.QueryConstant.SELECT_LECTURES_BY_USER_AND_WEEK_DAY;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {

    @Query(nativeQuery = true,
            value = SELECT_LECTURES_BY_USER_AND_WEEK_DAY)
    List<Lecture> findAllLecturesById(Long userId, String weekDay);
}
