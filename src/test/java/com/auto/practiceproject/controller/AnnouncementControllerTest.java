package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.AnnouncementRequestDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AnnouncementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllNotOnModerationAnnouncement() throws Exception {
        mockMvc.perform(get("/api/announcement"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L))
                .andExpect(jsonPath("$.content.[1].id").value(1L))
                .andExpect(jsonPath("$.content.[2].id").value(4L));
    }

    @Test
    public void getAnnouncementByIdTest() throws Exception {
        mockMvc.perform(get("/api/announcement/{id}", 3L))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L));
    }

    @Test
    public void createAnnouncementTest() {
        AnnouncementRequestDTO requestDTO = new AnnouncementRequestDTO();
    }

}
