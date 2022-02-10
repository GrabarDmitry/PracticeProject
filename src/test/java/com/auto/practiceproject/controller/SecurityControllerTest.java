package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.AuthenticationRequestDTO;
import com.auto.practiceproject.controller.dto.request.UserCreateDTO;
import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
import com.auto.practiceproject.util.ResponseDTOTestHelper;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class SecurityControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private TestUtil testUtil;

  @Autowired private ResponseDTOTestHelper responseDTOTestHelper;

  @Test
  public void authenticationTest() throws Exception {
    AuthenticationRequestDTO authenticationRequestDTO =
        new AuthenticationRequestDTO("Dzmitry@mail.ru", "12345");

    mockMvc
        .perform(testUtil.postJson("/auth", authenticationRequestDTO))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.token").isNotEmpty());
  }

  @Test
  public void authenticationWrongPasswordTest() throws Exception {
    AuthenticationRequestDTO authenticationRequestDTO =
        new AuthenticationRequestDTO("Dzmitry@mail.ru", "1234523r");

    testUtil.exceptionCheck(
        mockMvc
            .perform(testUtil.postJson("/auth", authenticationRequestDTO))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
        "400",
        "Bad Request",
        "Invalid Username or password!");
  }

  @Test
  public void authenticationWrongEmailTest() throws Exception {
    AuthenticationRequestDTO authenticationRequestDTO =
        new AuthenticationRequestDTO("Dzasdamitry@mail.ru", "12345");

    testUtil.exceptionCheck(
        mockMvc
            .perform(testUtil.postJson("/auth", authenticationRequestDTO))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
        "400",
        "Bad Request",
        "Invalid Username or password!");
  }

  @Test
  public void registrationTest() throws Exception {
    UserCreateDTO userCreateDTO = new UserCreateDTO("alex@mail.ru", "alex", "smith", "123");

    responseDTOTestHelper.userResponseDTOCheck(
        mockMvc
            .perform(testUtil.postJson("/registration", userCreateDTO))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
        new UserResponseDTO(4L, "alex@mail.ru", "alex", "smith", 4L));
  }

  @Test
  public void registrationWithExistEmailFailTest() throws Exception {
    UserCreateDTO userCreateDTO = new UserCreateDTO("Dzmitry@mail.ru", "alex", "smith", "123");

    mockMvc
        .perform(testUtil.postJson("/registration", userCreateDTO))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.httpStatusCode").value("400"))
        .andExpect(jsonPath("$.httpStatusType").value("Bad Request"))
        .andExpect(jsonPath("$.message.email").value("user with installed email already exists"));
  }
}
