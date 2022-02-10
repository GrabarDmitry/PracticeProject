package com.auto.practiceproject.controller;

import com.auto.practiceproject.controller.dto.request.AnnouncementActiveChangeDTO;
import com.auto.practiceproject.controller.dto.request.AnnouncementRequestDTO;
import com.auto.practiceproject.controller.dto.response.AnnouncementResponseDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application_test.properties")
@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AnnouncementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestUtil testUtil;

    @Autowired
    private ResponseDTOTestHelper responseDTOTestHelper;

    @Test
    public void getAllNotOnModerationAndSortByRatingAnnouncementsTest() throws Exception {
        mockMvc.perform(get("/api/announcement"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(2L))
                .andExpect(jsonPath("$.content.[1].id").value(1L))
                .andExpect(jsonPath("$.content.[2].id").value(4L));
    }

    @Test
    public void getAllAnnouncementsWithPageableTest() throws Exception {
        mockMvc.perform(get("/api/announcement").
                        queryParam("size", "1")
                        .queryParam("page", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.[0].id").value(4L))
                .andExpect(jsonPath("$.size").value(1))
                .andExpect(jsonPath("$.number").value(2));
    }

    @Test
    public void getAnnouncementByIdTest() throws Exception {
        mockMvc.perform(get("/api/announcement/{id}", 3L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L));
    }

    @Test
    public void getAnnouncementByIdFailTest() throws Exception {
        testUtil.exceptionCheck(
                mockMvc.perform(get("/api/announcement/{id}", 10L))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                ,
                "400",
                "Bad Request",
                "Announcement with Id: 10 not found");
    }

    @Test
    public void createAnnouncementTest() throws Exception {
        AnnouncementRequestDTO requestDTO = new AnnouncementRequestDTO(1L, "descriptionTest",
                "375296836539", 4000D, true, 1L, 1L, 43,
                2000, "23759375678394751", 3L, 0D
        );
        responseDTOTestHelper.announcementResponseDTOCheck(mockMvc.perform(testUtil.postJson("/api/announcement", requestDTO)
                                .with(testUtil.authentication("Dzmitry@mail.ru")))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                new AnnouncementResponseDTO(5L, "BMW X5", "descriptionTest", "375296836539",
                        4000D, true, true, 0D, 1L, 3L, 5L));
    }

    @Test
    public void announcementRatingUpTest() throws Exception {
        mockMvc.perform(post("/api/announcement/{id}/up", 3L).
                        with(testUtil.authentication("Dzmitry@mail.ru")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L));
    }

    @Test
    public void announcementRatingUpFailPermissionTest() throws Exception {
        testUtil.exceptionCheck(mockMvc.perform(post("/api/announcement/{id}/up", 2L).
                                with(testUtil.authentication("Dzmitry@mail.ru")))
                        .andDo(print())
                        .andExpect(status().isForbidden())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "403",
                "Forbidden",
                "You do not have permission to access this resource!"
        );
    }

    @Test
    public void announcementRatingUpFailNotEnoughMoneyTest() throws Exception {
        testUtil.exceptionCheck(mockMvc.perform(post("/api/announcement/{id}/up", 2L).
                                with(testUtil.authentication("user@mail.ru")))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "400",
                "Bad Request",
                "You don't have enough money to carry out the operation!"
        );
    }

    @Test
    public void changeAnnouncementActiveTest() throws Exception {
        AnnouncementActiveChangeDTO activeChangeDTO =
                new AnnouncementActiveChangeDTO(false);
        mockMvc.perform(testUtil.
                        patchJson("/api/announcement/{id}", activeChangeDTO, 3L).
                        with(testUtil.authentication("Dzmitry@mail.ru")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.isActive").value(false));
    }

    @Test
    public void changeAnnouncementActiveFailPermissionTest() throws Exception {
        AnnouncementActiveChangeDTO activeChangeDTO =
                new AnnouncementActiveChangeDTO(false);
        testUtil.exceptionCheck(mockMvc.perform(testUtil.
                                patchJson("/api/announcement/{id}", activeChangeDTO, 3L).
                                with(testUtil.authentication("user@mail.ru")))
                        .andExpect(status().isForbidden())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "403",
                "Forbidden",
                "You do not have permission to access this resource!");
    }

    @Test
    public void updateAnnouncementTest() throws Exception {
        AnnouncementRequestDTO requestDTO = new AnnouncementRequestDTO(1L, "descriptionTest",
                "375296836539", 4000D, true, 1L, 1L, 43,
                2000, "23759375678394751", 3L, 0D
        );
        responseDTOTestHelper.announcementResponseDTOCheck(mockMvc.perform(testUtil.putJson("/api/announcement/{id}",
                                        requestDTO, 3L)
                                .with(testUtil.authentication("Dzmitry@mail.ru")))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                new AnnouncementResponseDTO(3L, "BMW X5", "descriptionTest", "375296836539",
                        4000D, true, true, 0D, 1L, 3L, 3L));
    }

    @Test
    public void updateAnnouncementFailPermissionTest() throws Exception {
        AnnouncementRequestDTO requestDTO = new AnnouncementRequestDTO(1L, "descriptionTest",
                "375296836539", 4000D, true, 1L, 1L, 43,
                2000, "23759375678394751", 3L, 0D
        );
        testUtil.exceptionCheck(mockMvc.perform(testUtil.putJson("/api/announcement/{id}",
                                        requestDTO, 10L)
                                .with(testUtil.authentication("Dzmitry@mail.ru")))
                        .andExpect(status().isForbidden())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
                "403",
                "Forbidden",
                "You do not have permission to access this resource!"
        );
    }

}
