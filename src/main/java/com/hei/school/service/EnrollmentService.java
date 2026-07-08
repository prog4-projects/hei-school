package com.hei.school.service;

import com.hei.school.endpoint.event.EventProducer;
import com.hei.school.endpoint.event.model.SendEmailRequested;
import com.hei.school.repository.CourseRepository;
import com.hei.school.repository.EnrollmentRepository;
import com.hei.school.repository.UserRepository;
import com.hei.school.repository.model.JEnrollment;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentService {

  private final EnrollmentRepository enrollmentRepository;
  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final EventProducer<SendEmailRequested> eventProducer;

  @Transactional
  public void enroll(UUID userId, UUID courseId) {

    if (enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)) {
      throw new RuntimeException("User already enrolled");
    }

    var user = userRepository.findById(userId).orElseThrow();
    var course = courseRepository.findById(courseId).orElseThrow();

    JEnrollment enrollment =
        JEnrollment.builder().userId(userId).courseId(courseId).enrolledAt(Instant.now()).build();

    enrollmentRepository.save(enrollment);

    var emailEvent =
        SendEmailRequested.builder()
            .to(user.getEmail())
            .subject("Inscription confirmed")
            .htmlBody("You have been registered for : " + course.getTitle())
            .build();
    try {
      eventProducer.accept(List.of(emailEvent));
    } catch (Exception e) {
      log.warn("Failed to send email event (OK in local dev): {}", e.getMessage());
    }
  }
}
