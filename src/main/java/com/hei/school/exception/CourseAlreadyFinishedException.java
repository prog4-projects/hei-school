package com.hei.school.exception;

import org.springframework.http.HttpStatus;

public class CourseAlreadyFinishedException extends ApiException {

  public CourseAlreadyFinishedException() {
    super("Course already finished", HttpStatus.BAD_REQUEST);
  }
}
