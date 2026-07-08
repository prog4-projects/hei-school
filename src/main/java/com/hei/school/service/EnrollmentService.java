package com.hei.school.service;

import com.hei.school.enrollment.event.EnrollmentCreatedEvent;
import com.hei.school.exception.CourseAlreadyFinishedException;
import com.hei.school.exception.CourseNotFoundException;
import com.hei.school.exception.UserAlreadyEnrolledException;
import com.hei.school.exception.UserNotFoundException;
import com.hei.school.repository.CourseRepository;
import com.hei.school.repository.EnrollmentRepository;
import com.hei.school.repository.UserRepository;
import com.hei.school.repository.model.JEnrollment;
import java.time.Instant;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final ApplicationEventPublisher eventPublisher;
  private final EnrollmentRepository enrollmentRepository;

  @Transactional
  public void enroll(UUID userId, UUID courseId) {

    var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

    var course = courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);

    if (course.getEndDate().isBefore(Instant.now())) {
      throw new CourseAlreadyFinishedException();
    }

    if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
      throw new UserAlreadyEnrolledException();
    }

    JEnrollment enrollment =
        JEnrollment.builder()
            .userId(user.getId())
            .courseId(course.getId())
            .enrolledAt(Instant.now())
            .build();

    enrollmentRepository.save(enrollment);

    eventPublisher.publishEvent(
        new EnrollmentCreatedEvent(user.getFirstName(), course.getTitle(), user.getEmail()));
  }
}
