package com.hei.school.service.event;

import com.hei.school.endpoint.event.model.SendEmailRequested;
import com.hei.school.mail.Email;
import com.hei.school.mail.Mailer;
import com.hei.school.service.CourseService;
import com.hei.school.service.UserService;
import jakarta.mail.internet.InternetAddress;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendEmailRequestedService implements Consumer<SendEmailRequested> {
  private final Mailer mailer;
  private final UserService userService;
  private final CourseService courseService;

  @SneakyThrows
  @Override
  public void accept(SendEmailRequested sendEmailRequested) {
    var user = userService.getById(sendEmailRequested.getUserId());
    var course = courseService.getById(sendEmailRequested.getCourseId());
    var to = user.getEmail();
    var subject = "Enrollment confirmation : %s".formatted(course.getTitle());
    var htmlBody =
        """
        <html>
          <body>
            <p>Dear %s,</p>
            <p>Your subscription has been confirmed. You now have full access to your course.</p>
            <p>Thank you for joining us !</p>
            <p>We hope you will come, best regards</p>
            <p>PojaClassTeam</p>
          </body>
        </html>
        """
            .formatted(user.getUserName());
    var email =
        new Email(new InternetAddress(to), List.of(), List.of(), subject, htmlBody, List.of());
    mailer.accept(email);
  }
}
