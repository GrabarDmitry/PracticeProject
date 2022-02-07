package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.BookmarkAnnouncementChangeDTO;
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
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtil testUtil;

    @Test
    public void getAllAnnouncementInBookmarkTest() throws Exception {
        mockMvc.perform(get("/api/bookmark")
                        .with(testUtil.authentication("Dzmitry@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(2L));
    }

    @Test
    public void changeBookmarkAnnouncementsTest() throws Exception {
        BookmarkAnnouncementChangeDTO announcementChangeDTO = new BookmarkAnnouncementChangeDTO(3L);
        mockMvc.perform(testUtil.
                        patchJson("/api/bookmark", announcementChangeDTO).
                        with(testUtil.authentication("Dzmitry@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(2L))
                .andExpect(jsonPath("$.[1].id").value(3L));
    }

    @Test
    public void changeBookmarkAnnouncementsFailTest() throws Exception {
        BookmarkAnnouncementChangeDTO announcementChangeDTO = new BookmarkAnnouncementChangeDTO(2L);
        testUtil.exceptionCheck(mockMvc.perform(testUtil.
                                patchJson("/api/bookmark", announcementChangeDTO).
                                with(testUtil.authentication("Dzmitry@mail.ru")))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "400",
                "Bad Request",
                "This announcement is bookmarked!"
        );
    }

}
