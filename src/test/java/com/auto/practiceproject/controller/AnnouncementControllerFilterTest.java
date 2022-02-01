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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application_controller_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AnnouncementControllerFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtil testUtil;

    @Test
    public void filterByBrand() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "brand.eq(BMW)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(1L))
                .andExpect(jsonPath("$.content.[1].id").value(4L));
    }

    @Test
    public void filterByModel() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "model.eq(X5)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(1L));
    }

    @Test
    public void filterByPrice() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "price.eq(5000)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(1L))
                .andExpect(jsonPath("$.content.[1].id").value(4L));
    }

    @Test
    public void filterByReleasedYear() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "releasedYear.eq(2020-01-01)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(1L));
    }

    @Test
    public void filterByMileage() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "mileage.eq(223)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L));
    }

    @Test
    public void filterByEngineCapacity() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "engineCapacity.eq(1900)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L));
    }

    @Test
    public void filterByEngineType() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "engineType.eq(Electro)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(1L));
    }

    @Test
    public void filterByTransmission() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "transmission.eq(Manual)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L))
                .andExpect(jsonPath("$.content.[1].id").value(4L));
    }

    @Test
    public void filterByRegion() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "region.eq(Grodno)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L))
                .andExpect(jsonPath("$.content.[1].id").value(4L));
    }

    @Test
    public void filterByCustomsDuty() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "customsDuty.eq(0)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L))
                .andExpect(jsonPath("$.content.[1].id").value(1L));
    }

    @Test
    public void filterByIsExchange() throws Exception {
        this.mockMvc.perform(testUtil.getWithFilter("/api/announcement", "isExchange.eq(true)"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L));
    }

}
