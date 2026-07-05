package com.hei.school.enrollment.event;

import com.hei.school.mail.Email;
import com.hei.school.mail.Mailer;
import com.hei.school.repository.CourseRepository;
import com.hei.school.repository.UserRepository;
import jakarta.mail.internet.InternetAddress;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EnrollmentListener {

  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final Mailer mailer;

  @Async
  @TransactionalEventListener
  @SneakyThrows
  public void handle(EnrollmentCreatedEvent event) {

    var user = userRepository.findById(event.userId()).orElseThrow();

    var course = courseRepository.findById(event.courseId()).orElseThrow();

    var email =
        new Email(
            new InternetAddress(user.getEmail()),
            List.<InternetAddress>of(),
            List.<InternetAddress>of(),
            "Inscription confirmed",
            "You have been registered for : " + course.getTitle(),
            List.<File>of());

    mailer.accept(email);
  }
}
