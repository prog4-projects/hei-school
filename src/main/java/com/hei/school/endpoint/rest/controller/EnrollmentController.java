package com.hei.school.endpoint.rest.controller;

import com.hei.school.service.EnrollmentService;
import jakarta.mail.internet.AddressException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  @PostMapping("/{userId}/enrollments/{courseId}")
  public ResponseEntity<Void> enroll(@PathVariable UUID userId, @PathVariable UUID courseId)
      throws AddressException {

    enrollmentService.enroll(userId, courseId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
