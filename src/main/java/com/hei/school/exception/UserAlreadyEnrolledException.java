package com.hei.school.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyEnrolledException extends ApiException {

  public UserAlreadyEnrolledException() {
    super("User already enrolled", HttpStatus.CONFLICT);
  }
}
