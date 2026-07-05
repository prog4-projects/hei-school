package com.hei.school.enrollment.event;

import com.hei.school.repository.CourseRepository;
import com.hei.school.repository.UserRepository;
import com.hei.school.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EnrollmentListener {

  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final MailService mailService;

  @Async
  @TransactionalEventListener
  public void handle(EnrollmentCreatedEvent event) {

    var user = userRepository.findById(event.getUserId()).orElseThrow();

    var course = courseRepository.findById(event.getCourseId()).orElseThrow();

    mailService.send(
        user.getEmail(),
        "Inscription confirmée",
        "Vous êtes inscrit au cours : " + course.getTitle());
  }
}
