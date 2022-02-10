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
public class RegionControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private TestUtil testUtil;

  @Test
  public void getAllRegionTest() throws Exception {
    mockMvc
        .perform(get("/api/region"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content.[0].id").value(1L))
        .andExpect(jsonPath("$.content.[1].id").value(2L))
        .andExpect(jsonPath("$.content.[2].id").value(3L))
        .andExpect(jsonPath("$.content.[3].id").value(4L))
        .andExpect(jsonPath("$.content.[4].id").value(5L));
  }

  @Test
  public void getAllRegionWithPageableTest() throws Exception {
    mockMvc
        .perform(
            get("/api/region")
                .queryParam("page", "1")
                .queryParam("size", "3")
                .queryParam("sort", "title"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.content.[0].id").value(3L))
        .andExpect(jsonPath("$.content.[1].id").value(2L))
        .andExpect(jsonPath("$.content.[2].id").value(4L))
        .andExpect(jsonPath("$.size").value(3))
        .andExpect(jsonPath("$.number").value(1));
  }

  @Test
  public void getRegionByIdTest() throws Exception {
    mockMvc
        .perform(get("/api/region/{id}", 3L))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(3L));
  }

  @Test
  public void getRegionByIdFailTest() throws Exception {
    testUtil.exceptionCheck(
        mockMvc
            .perform(get("/api/region/{id}", 10L))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
        "400",
        "Bad Request",
        "Region with Id: 10 not found");
  }
}
