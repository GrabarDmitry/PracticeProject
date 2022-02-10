//package com.auto.practiceproject.controller;
//
//import com.auto.practiceproject.controller.dto.response.UserResponseDTO;
//import com.auto.practiceproject.util.ResponseDTOTestHelper;
//import com.auto.practiceproject.util.TestUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application_test.properties")
//@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestUtil testUtil;
//
//    @Autowired
//    private ResponseDTOTestHelper responseDTOTestHelper;
//
//    @Test
//    public void getCurrentUserTest() throws Exception {
//        responseDTOTestHelper.userResponseDTOCheck(mockMvc.perform(get("/api/user").
//                                with(testUtil.authentication("Dzmitry@mail.ru")))
//                        .andDo(print())
//                        .andExpect(status().isOk())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
//                new UserResponseDTO(1L, "Dzmitry@mail.ru", "Dzmitry", "Hrabar", 1L));
//    }
//
//    @Test
//    public void getUserAnnouncementsTest() throws Exception {
//        mockMvc.perform(get("/api/user/announcement").
//                        with(testUtil.authentication("Dzmitry@mail.ru"))
//                        .queryParam("page", "0")
//                        .queryParam("size", "1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(3L))
//                .andExpect(jsonPath("$.size").value(1))
//                .andExpect(jsonPath("$.number").value(0));
//    }
//
//    @Test
//    public void getUserByIdTest() throws Exception {
//        mockMvc.perform(get("/api/user/{id}", 1L))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1L));
//    }
//
//    @Test
//    public void getUserByIdFailTest() throws Exception {
//        testUtil.exceptionCheck(
//                mockMvc.perform(get("/api/user/{id}", 10L))
//                        .andDo(print())
//                        .andExpect(status().isBadRequest())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                ,
//                "400",
//                "Bad Request",
//                "User with Id: 10 not found");
//    }
//
//}
