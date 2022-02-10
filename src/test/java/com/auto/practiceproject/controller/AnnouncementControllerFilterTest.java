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
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application_test.properties")
//@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//public class AnnouncementControllerFilterTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TestUtil testUtil;
//
//    @Test
//    public void filterByBrandTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "autoBrandId.eq.1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L))
//                .andExpect(jsonPath("$.content.[1].id").value(4L));
//    }
//
//    @Test
//    public void filterByModelTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "autoModelId.eq.1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L));
//    }
//
//    @Test
//    public void filterByPriceTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "price.eq.5000"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L))
//                .andExpect(jsonPath("$.content.[1].id").value(4L));
//    }
//
//    @Test
//    public void filterByPriceMoreThanTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "price.more.4000"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L))
//                .andExpect(jsonPath("$.content.[1].id").value(1L))
//                .andExpect(jsonPath("$.content.[2].id").value(4L));
//    }
//
//    @Test
//    public void filterByPriceLessThanTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "price.less.8000"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L))
//                .andExpect(jsonPath("$.content.[1].id").value(4L));
//    }
//
//    @Test
//    public void filterByReleasedYearTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "autoReleasedYearId.eq.1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L));
//    }
//
//    @Test
//    public void filterByMileageTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "mileage.eq.223"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L));
//    }
//
//    @Test
//    public void filterByMileageMoreThanTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "mileage.more.1000"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L))
//                .andExpect(jsonPath("$.content.[1].id").value(4L));
//    }
//
//    @Test
//    public void filterByMileageLessThanTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "mileage.less.1000"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L));
//    }
//
//    @Test
//    public void filterByEngineCapacityTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "engineCapacity.eq.1900"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L));
//    }
//
//    @Test
//    public void filterByEngineCapacityMoreThanTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "engineCapacity.more.1900"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L));
//    }
//
//    @Test
//    public void filterByEngineCapacityLessThanTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "engineCapacity.less.1900"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(4L));
//    }
//
//    @Test
//    public void filterByEngineTypeTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "autoEngineId.eq.3"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(1L));
//    }
//
//    @Test
//    public void filterByTransmissionTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "autoTransmissionId.eq.2"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L))
//                .andExpect(jsonPath("$.content.[1].id").value(4L));
//    }
//
//    @Test
//    public void filterByRegionTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "regionId.eq.3"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L))
//                .andExpect(jsonPath("$.content.[1].id").value(4L));
//    }
//
//    @Test
//    public void filterByCustomsDutyTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "customsDuty.eq.0"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L))
//                .andExpect(jsonPath("$.content.[1].id").value(1L));
//    }
//
//    @Test
//    public void filterByIsExchangeTest() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "isExchange.eq.true"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(2L));
//    }
//
//    @Test
//    public void applyAllFilters() throws Exception {
//        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement",
//                        "autoBrandId.eq.1;autoReleasedYearId.eq.3;autoModelId.eq.2;price.eq.5000;mileage.eq.232323;" +
//                                "engineCapacity.eq.1000;autoEngineId.eq.1;autoTransmissionId.eq.2;regionId.eq.3;customsDuty.eq.23;" +
//                                "isExchange.eq.false"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.content.[0].id").value(4L));
//    }
//
//    @Test
//    public void filterFailTest() throws Exception {
//        testUtil.exceptionCheck(this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "test.eq.test"))
//                        .andDo(print())
//                        .andExpect(status().isBadRequest())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
//                "400",
//                "Bad Request",
//                "Filter parameter is incorrect");
//    }
//
//}
