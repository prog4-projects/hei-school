package com.hei.school.service;

import com.hei.school.entity.Course;
import com.hei.school.mapper.CourseMapper;
import com.hei.school.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public Course getById(UUID id) {
    return courseMapper.toDomain(
        courseRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Course with id %s not found !".formatted(id))));
  }
}
