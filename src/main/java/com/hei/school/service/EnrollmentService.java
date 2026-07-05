package com.hei.school.service;

import com.hei.school.event.EnrollmentCreatedEvent;
import com.hei.school.repository.EnrollmentRepository;
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

  private final EnrollmentRepository enrollmentRepository;
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public void enroll(UUID userId, UUID courseId) {

    if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
      throw new RuntimeException("User already enrolled");
    }

    JEnrollment enrollment =
        JEnrollment.builder().userId(userId).courseId(courseId).enrolledAt(Instant.now()).build();

    enrollmentRepository.save(enrollment);

    eventPublisher.publishEvent(new EnrollmentCreatedEvent(userId, courseId));
  }
}
