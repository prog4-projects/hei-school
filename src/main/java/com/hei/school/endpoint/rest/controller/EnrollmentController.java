package com.hei.school.endpoint.rest.controller;

import com.hei.school.service.EnrollmentService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  @PostMapping("/{userId}/courses/{courseId}/enroll")
  public ResponseEntity<Void> enroll(@PathVariable UUID userId, @PathVariable UUID courseId) {
    enrollmentService.enroll(userId, courseId);
    return ResponseEntity.ok().build();
  }
}
