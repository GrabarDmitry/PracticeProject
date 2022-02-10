package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.AnnouncementModerationChangeDTO;
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
@TestPropertySource(
        locations = "classpath:application_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ModeratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtil testUtil;

    @Test
    public void getAllNotModerationAnnouncementTest() throws Exception {
        mockMvc.perform(get("/api/moderator/announcement").
                        with(testUtil.authentication("user@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(3L));
    }

    @Test
    public void getAllNotModerationAnnouncementWithPageableTest() throws Exception {
        mockMvc.perform(get("/api/moderator/announcement").
                        with(testUtil.authentication("user@mail.ru"))
                        .queryParam("page", "0")
                        .queryParam("size", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(3L))
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.number").value(0));
    }

    @Test
    public void getAllNotModerationAnnouncementFailPermissionTest() throws Exception {
        testUtil.exceptionCheck(mockMvc.perform(get("/api/moderator/announcement").
                                with(testUtil.authentication("Jack@mail.ru")))
                        .andDo(print())
                        .andExpect(status().isForbidden())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "403",
                "Forbidden",
                "You do not have permission to access this resource!"
        );
    }

    @Test
    public void changeAnnouncementIsModerationTest() throws Exception {
        AnnouncementModerationChangeDTO moderationChangeDTO =
                new AnnouncementModerationChangeDTO(true);
        mockMvc.perform(testUtil.
                        patchJson("/api/moderator/announcement/{id}", moderationChangeDTO, 3L).
                        with(testUtil.authentication("Dzmitry@mail.ru")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L));
    }

    @Test
    public void changeAnnouncementIsActiveFailPermissionTest() throws Exception {
        AnnouncementModerationChangeDTO moderationChangeDTO =
                new AnnouncementModerationChangeDTO(true);
        testUtil.exceptionCheck(mockMvc.perform(testUtil.
                                patchJson("/api/moderator/announcement/{id}", moderationChangeDTO, 3L).
                                with(testUtil.authentication("Jack@mail.ru")))
                        .andExpect(status().isForbidden())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "403",
                "Forbidden",
                "You do not have permission to access this resource!");
    }


}
