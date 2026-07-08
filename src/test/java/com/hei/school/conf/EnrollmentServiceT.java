package com.hei.school.conf;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hei.school.enrollment.event.EnrollmentCreatedEvent;
import com.hei.school.repository.CourseRepository;
import com.hei.school.repository.EnrollmentRepository;
import com.hei.school.repository.UserRepository;
import com.hei.school.repository.model.JCourse;
import com.hei.school.repository.model.JEnrollment;
import com.hei.school.repository.model.JUser;
import com.hei.school.service.EnrollmentService;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

  @Mock private UserRepository userRepository;

  @Mock private CourseRepository courseRepository;

  @Mock private EnrollmentRepository enrollmentRepository;

  @Mock private ApplicationEventPublisher eventPublisher;

  @InjectMocks private EnrollmentService enrollmentService;

  @Test
  void should_create_enrollment_successfully() {

    UUID userId = UUID.randomUUID();
    UUID courseId = UUID.randomUUID();

    JUser user =
        JUser.builder()
            .id(userId)
            .firstName("Heri")
            .lastName("Test")
            .email("heri@test.com")
            .userName("heri")
            .build();

    JCourse course =
        JCourse.builder()
            .id(courseId)
            .title("Spring Boot")
            .startDate(Instant.now())
            .endDate(Instant.now().plusSeconds(3600))
            .build();

    when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

    when(courseRepository.findById(courseId)).thenReturn(java.util.Optional.of(course));

    when(enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)).thenReturn(false);

    enrollmentService.enroll(userId, courseId);

    verify(enrollmentRepository).save(any(JEnrollment.class));

    verify(eventPublisher).publishEvent(any(EnrollmentCreatedEvent.class));
  }

  @Test
  void should_fail_when_user_already_enrolled() {

    UUID userId = UUID.randomUUID();
    UUID courseId = UUID.randomUUID();

    when(enrollmentRepository.existsByUserIdAndCourseId(userId, courseId)).thenReturn(true);

    assertThrows(RuntimeException.class, () -> enrollmentService.enroll(userId, courseId));
  }
}
