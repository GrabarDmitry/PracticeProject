package com.auto.practiceproject.exception.handler;

import com.auto.practiceproject.exception.FilterException;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.exception.WalletOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler({
    BadCredentialsException.class,
    ResourceException.class,
    FilterException.class,
    WalletOperationException.class
  })
  public ResponseEntity<ExceptionInfo> badCredentialsExceptionHandle(Exception ex) {
    log.error("{}: {}", ex.getClass(), ex.getMessage());
    return ResponseEntity.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ExceptionInfo(HttpStatus.BAD_REQUEST, ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidateExceptionInfo> handleValidateConflicts(
      MethodArgumentNotValidException ex) {
    log.error("{}: {}", ex.getClass(), ex.getMessage());

    Map<String, List<String>> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              if (error instanceof FieldError) {
                ConstraintViolation<?> o = error.unwrap(ConstraintViolation.class);
                String errorField = ((FieldError) error).getField();
                if (o.getConstraintDescriptor().getAttributes().containsKey("field")) {
                  errorField += "." + o.getConstraintDescriptor().getAttributes().get("field");
                }
                if (errors.containsKey(errorField)) {
                  errors.get(errorField).add(((FieldError) error).getDefaultMessage());
                } else {
                  ArrayList<String> arr = new ArrayList<>();
                  arr.add(((FieldError) error).getDefaultMessage());
                  errors.put(errorField, arr);
                }
              } else {
                ConstraintViolation<?> o = error.unwrap(ConstraintViolation.class);
                String fieldName =
                    (String) o.getConstraintDescriptor().getAttributes().get("field");
                if (errors.containsKey(fieldName)) {
                  errors.get(fieldName).add(error.getDefaultMessage());
                } else {
                  ArrayList<String> arr = new ArrayList<>();
                  arr.add(error.getDefaultMessage());
                  errors.put(fieldName, arr);
                }
              }
            });

    return ResponseEntity.badRequest()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new ValidateExceptionInfo(HttpStatus.BAD_REQUEST, errors));
  }
}
