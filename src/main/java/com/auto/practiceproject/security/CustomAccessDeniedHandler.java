package com.auto.practiceproject.security;

import com.auto.practiceproject.exception.handler.ExceptionInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
      throws IOException, ServletException {

    ExceptionInfo errorResponse =
        new ExceptionInfo(
            HttpStatus.FORBIDDEN, "You do not have permission to access this resource!");

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");
    response.getWriter().write(convertObjectToJson(errorResponse));
  }

  private String convertObjectToJson(Object object) throws JsonProcessingException {
    if (object == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }
}
