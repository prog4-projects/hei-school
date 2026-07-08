package com.hei.school.mapper;

import com.hei.school.entity.Course;
import com.hei.school.repository.model.JCourse;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

  public Course toDomain(JCourse jCourse) {

    return Course.builder()
        .id(jCourse.getId())
        .title(jCourse.getTitle())
        .startDate(jCourse.getStartDate())
        .endDate(jCourse.getEndDate())
        .build();
  }

  public JCourse toEntity(Course course) {

    return JCourse.builder()
        .id(course.getId())
        .title(course.getTitle())
        .startDate(course.getStartDate())
        .endDate(course.getEndDate())
        .build();
  }
}
