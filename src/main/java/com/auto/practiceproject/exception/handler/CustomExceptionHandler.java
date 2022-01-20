package com.auto.practiceproject.exception.handler;

import com.auto.practiceproject.exception.FilterException;
import com.auto.practiceproject.exception.ResourceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
            FilterException.class
    })
    public ResponseEntity<ExceptionInfo> badCredentialsExceptionHandle(Exception ex) {
        log.error("{}: {}", ex.getClass(), ex.getMessage());
        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ExceptionInfo(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateExceptionInfo> validateExceptionHandle(MethodArgumentNotValidException ex) {

        log.error("{}: {}", ex.getClass(), ex.getMessage());

        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (errors.containsKey(((FieldError) error).getField())) {
                errors.get(((FieldError) error).getField()).add(((FieldError) error).getDefaultMessage());
            } else {
                ArrayList<String> arr = new ArrayList<>();
                arr.add(((FieldError) error).getDefaultMessage());
                errors.put(((FieldError) error).getField(), arr);
            }
        });

        return ResponseEntity
                .badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ValidateExceptionInfo(
                        HttpStatus.BAD_REQUEST,
                        errors));
    }

}
