package com.hei.school.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", Instant.now());
    body.put("message", ex.getMessage());
    body.put("status", ex.getStatus().value());

    return ResponseEntity.status(ex.getStatus()).body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {

    Map<String, Object> body = new HashMap<>();
    body.put("timestamp", Instant.now());
    body.put("message", "Internal server error");
    body.put("status", 500);

    return ResponseEntity.status(500).body(body);
  }
}
