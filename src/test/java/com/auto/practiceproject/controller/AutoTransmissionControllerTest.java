//package com.auto.practiceproject.controller;
//
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
//public class AutoTransmissionControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestUtil testUtil;
//
//    @Test
//    public void getAllAutoTransmissionTest() throws Exception {
//        mockMvc.perform(get("/api/transmission"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].id").value(1L))
//                .andExpect(jsonPath("$.[0].type").value("Automatic"))
//                .andExpect(jsonPath("$.[1].id").value(2L))
//                .andExpect(jsonPath("$.[1].type").value("Manual"));
//    }
//
//    @Test
//    public void getAutoTransmissionByIdTest() throws Exception {
//        mockMvc.perform(get("/api/transmission/{id}", 1L))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1L));
//    }
//
//    @Test
//    public void getAutoTransmissionByIdFailTest() throws Exception {
//        testUtil.exceptionCheck(
//                mockMvc.perform(get("/api/transmission/{id}", 10L))
//                        .andDo(print())
//                        .andExpect(status().isBadRequest())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                ,
//                "400",
//                "Bad Request",
//                "Auto transmission with Id: 10 not found");
//    }
//
//
//}
