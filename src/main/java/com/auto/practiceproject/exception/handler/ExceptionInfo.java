package com.auto.practiceproject.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExceptionInfo {

  private String httpStatusCode;
  private String httpStatusType;
  private String message;

  public ExceptionInfo(HttpStatus httpStatus, String message) {
    this.httpStatusCode = String.valueOf(httpStatus.value());
    this.httpStatusType = httpStatus.getReasonPhrase();
    this.message = message;
  }
}
