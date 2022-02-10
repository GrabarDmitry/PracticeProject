package com.auto.practiceproject.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
public class ValidateExceptionInfo {

  private String httpStatusCode;
  private String httpStatusType;
  private Map<String, List<String>> message;

  public ValidateExceptionInfo(HttpStatus httpStatus, Map<String, List<String>> message) {
    this.httpStatusCode = String.valueOf(httpStatus.value());
    this.httpStatusType = httpStatus.getReasonPhrase();
    this.message = message;
  }
}
