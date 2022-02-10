//package com.auto.practiceproject.controller;
//
//import com.auto.practiceproject.controller.dto.request.ModeratorUserCreateDTO;
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
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application_test.properties")
//@Sql(value = "classpath:init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//public class AdminControllerTest {
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
//    public void createModeratorUserTest() throws Exception {
//        ModeratorUserCreateDTO createDTO =
//                new ModeratorUserCreateDTO("moderator@mail.ru", "Jack", "Mini");
//
//        responseDTOTestHelper.userResponseDTOCheck(mockMvc.perform(testUtil.postJson("/api/admin/moderatorUser", createDTO)
//                                .with(testUtil.authentication("Dzmitry@mail.ru")))
//                        .andDo(print())
//                        .andExpect(status().isCreated())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
//                new UserResponseDTO(4L, "moderator@mail.ru", "Jack", "Mini", 4L)
//        );
//    }
//
//    @Test
//    public void createModeratorUserFailPermissionTest() throws Exception {
//        ModeratorUserCreateDTO createDTO =
//                new ModeratorUserCreateDTO("moderator@mail.ru", "Jack", "Mini");
//
//        testUtil.exceptionCheck(mockMvc.perform(testUtil.postJson("/api/admin/moderatorUser", createDTO)
//                                .with(testUtil.authentication("user@mail.ru")))
//                        .andDo(print())
//                        .andExpect(status().isForbidden())
//                        .andExpect(content().contentType(MediaType.APPLICATION_JSON)),
//                "403",
//                "Forbidden",
//                "You do not have permission to access this resource!"
//        );
//    }
//
//
//}
