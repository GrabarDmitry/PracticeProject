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
//public class AutoModelControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestUtil testUtil;
//
//    @Test
//    public void findAllAutoModelWithFilterByBrandTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/model", "autoBrandId.eq.1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].id").value(1L))
//                .andExpect(jsonPath("$.[1].id").value(2L));
//    }
//
//    @Test
//    public void findAllAutoModelWithFilterByReleasedYearTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/model", "autoReleasedYearId.eq.4"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].id").value(3L))
//                .andExpect(jsonPath("$.[1].id").value(5L));
//    }
//
//    @Test
//    public void findAllAutoModelWithFilterByTitleTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/model", "title.eq.Maybach"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].id").value(5L));
//    }
//
//    @Test
//    public void findAllAutoModelWithAllFilterTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/model",
//                        "title.eq.X3;autoReleasedYearId.eq.3;autoBrandId.eq.1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.[0].id").value(2L));
//    }
//
//    @Test
//    public void getAutoModelByIdTest() throws Exception {
//        mockMvc.perform(get("/api/model/{id}", 3L))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(3L));
//    }
//
//    @Test
//    public void getAutoModelByIdFailTest() throws Exception {
//        testUtil.exceptionCheck(
//                mockMvc.perform(get("/api/model/{id}", 10L))
//                        .andDo(print())
//                        .andExpect(status().isBadRequest())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                ,
//                "400",
//                "Bad Request",
//                "Auto model with Id: 10 not found");
//    }
//
//}
