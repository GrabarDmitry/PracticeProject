package com.auto.practiceproject.util;

import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.security.jwt.TokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Component
public class TestUtil {

    @Autowired
    private TokenProvider provider;

    @Autowired
    private UserDetailsService userDetailsService;

    public String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    public MockHttpServletRequestBuilder postJson(String uri, Object body) throws JsonProcessingException {
        String json = objectToJson(body);
        return post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
    }

    public MockHttpServletRequestBuilder patchJson(String uri, Object body, Long paramValue) throws JsonProcessingException {
        String json = objectToJson(body);
        return patch(uri, paramValue)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
    }

    public MockHttpServletRequestBuilder patchJson(String uri, Object body) throws JsonProcessingException {
        String json = objectToJson(body);
        return patch(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
    }

    public MockHttpServletRequestBuilder putJson(String uri, Object body, Long paramValue) throws JsonProcessingException {
        String json = objectToJson(body);
        return put(uri, paramValue)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
    }

    public MockHttpServletRequestBuilder getWithFilter(String uri, String filter) {
        return get(uri).queryParam("filter", filter);
    }

    public ResultActions exceptionCheck(ResultActions resultActions, String statusCode,
                                        String statusType, String message) throws Exception {
        return resultActions.andExpect(jsonPath("$.httpStatusCode").value(statusCode))
                .andExpect(jsonPath("$.httpStatusType").value(statusType))
                .andExpect(jsonPath("$.message").value(message));
    }


    public RequestPostProcessor authentication(String email) {
        return request -> {
            request.addHeader("Authorization", "Bearer "
                    + provider.createToken(
                    (UserDetailsImpl) userDetailsService.loadUserByUsername(email)
            ));
            return request;
        };
    }

}
