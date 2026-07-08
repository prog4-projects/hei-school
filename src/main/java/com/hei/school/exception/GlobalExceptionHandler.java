package com.hei.school.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    ex.printStackTrace();

    Map<String, Object> body = new HashMap<>();

    body.put("timestamp", Instant.now());
    body.put("message", "Internal server error");
    body.put("status", 500);

    return ResponseEntity.status(500).body(body);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Map<String, Object>> handleTypeMismatch(
      MethodArgumentTypeMismatchException ex) {

    Map<String, Object> body = new HashMap<>();

    String message =
        "Invalid value for parameter '"
            + ex.getName()
            + "'. Expected type: "
            + ex.getRequiredType().getSimpleName();

    body.put("timestamp", Instant.now());
    body.put("message", message);
    body.put("status", 400);

    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Map<String, Object>> handleDataIntegrity(
      DataIntegrityViolationException ex) {

    Map<String, Object> body = new HashMap<>();

    body.put("timestamp", Instant.now());
    body.put("message", "Conflict: resource already exists");
    body.put("status", HttpStatus.CONFLICT.value());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }
}
