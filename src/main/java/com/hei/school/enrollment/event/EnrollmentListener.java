package com.hei.school.enrollment.event;

import com.hei.school.mail.Email;
import com.hei.school.mail.Mailer;
import com.hei.school.repository.CourseRepository;
import com.hei.school.repository.UserRepository;
import jakarta.mail.internet.InternetAddress;
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

    Email email =
        new Email(
            new InternetAddress(event.email()),
            List.of(),
            List.of(),
            "Inscription confirmée",
            "Bonjour " + event.firstName() + ", vous êtes inscrit au cours " + event.courseTitle(),
            List.of());

    mailer.accept(email);
  }
}
