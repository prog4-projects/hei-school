package com.hei.school.service.event;

import com.hei.school.endpoint.event.model.SendEmailRequested;
import com.hei.school.mail.Email;
import com.hei.school.mail.Mailer;
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

  @SneakyThrows
  @Override
  public void accept(SendEmailRequested sendEmailRequested) {
    var recipientAddress = new InternetAddress(sendEmailRequested.getTo());
    var subject = sendEmailRequested.getSubject() != null ? sendEmailRequested.getSubject() : "";
    var htmlBody =
        sendEmailRequested.getHtmlBody() != null ? sendEmailRequested.getHtmlBody() : "... world!";
    mailer.accept(new Email(recipientAddress, List.of(), List.of(), subject, htmlBody, List.of()));
  }
}
