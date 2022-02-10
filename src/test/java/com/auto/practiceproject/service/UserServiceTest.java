//package com.auto.practiceproject.service;
//
//import com.auto.practiceproject.dao.UserDAO;
//import com.auto.practiceproject.model.User;
//import com.auto.practiceproject.security.UserDetailsImpl;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private UserDAO userDAO;
//
//    @MockBean
//    private Authentication authentication;
//
//    @MockBean
//    private SecurityContext securityContext;
//
//    @Before
//    public void setUp() {
//        User user = new User();
//        user.setEmail("test@mail.ru");
//
//        when(userDAO.findUserByEmail("test@mail.ru"))
//                .thenReturn(Optional.of(user));
//
//    }
//
//    @Test
//    public void findUserByMailTest() {
//        Optional<User> user = userService.findUserByEmail("test@mail.ru");
//        Assert.assertTrue(user.isPresent());
//        Assert.assertEquals(user.get().getEmail(), "test@mail.ru");
//    }
//
//    @Test
//    public void findUserByMailIsNullTest() {
//        Optional<User> user = userService.findUserByEmail("notest@mail.ru");
//        Assert.assertTrue(user.isEmpty());
//    }
//
//    @Test
//    public void getCurrentUser() {
//        User userTest = new User();
//        userTest.setId(1L);
//        userTest.setName("Dzmitry");
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(userTest);
//
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userDetails);
//
//        Optional<User> user = userService.getCurrentUser();
//
//        Assert.assertTrue(user.isPresent());
//        Assert.assertEquals(user.get().getName(), "Dzmitry");
//    }
//
//}
