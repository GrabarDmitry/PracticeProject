package com.auto.practiceproject.controller;

import com.auto.practiceproject.util.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AutoReleasedYearControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private TestUtil testUtil;

  @Test
  public void getAutoReleasedYearByIdTest() throws Exception {
    mockMvc
        .perform(get("/api/releasedYear/{id}", 3L))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(3L));
  }

  @Test
  public void getAutoReleasedYearByIdFailTest() throws Exception {
    testUtil.exceptionCheck(
        mockMvc
            .perform(get("/api/releasedYear/{id}", 10L))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
        "400",
        "Bad Request",
        "Auto released year with Id: 10 not found");
  }
}
