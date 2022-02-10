//package com.auto.practiceproject.service;
//
//
//import com.auto.practiceproject.controller.dto.request.MailDataDTO;
//import com.auto.practiceproject.dao.BookmarkDAO;
//import com.auto.practiceproject.dao.UserDAO;
//import com.auto.practiceproject.dao.WalletDAO;
//import com.auto.practiceproject.model.Bookmark;
//import com.auto.practiceproject.model.User;
//import com.auto.practiceproject.model.Wallet;
//import com.auto.practiceproject.security.UserDetailsImpl;
//import com.auto.practiceproject.security.jwt.TokenProvider;
//import com.auto.practiceproject.service.impl.UserDetailsServiceImpl;
//import com.auto.practiceproject.util.CustomUtil;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SecurityServiceTest {
//
//    @Autowired
//    private SecurityService securityService;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @MockBean
//    private UserDetailsServiceImpl userDetailsService;
//
//    @MockBean
//    private TokenProvider provider;
//
//    @MockBean
//    private UserDAO userDAO;
//
//    @MockBean
//    private BookmarkDAO bookmarkDAO;
//
//    @MockBean
//    private WalletDAO walletDAO;
//
//    @MockBean
//    private MailService mailService;
//
//    @MockBean
//    private CustomUtil customUtil;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void authenticationTest() {
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("dzmitry@mail.ru");
//        UserDetailsImpl userDetails = new UserDetailsImpl(user);
//
//        Mockito.when(userDetailsService.loadUserByUsername("dzmitry@mail.ru"))
//                .thenReturn(userDetails);
//        Mockito.when(provider.createToken(userDetails))
//                .thenReturn("test_token");
//
//        String token = securityService.authentication("dzmitry@mail.ru", "password");
//
//        Assert.assertNotNull(token);
//        Assert.assertEquals(token, "test_token");
//
//        Mockito.verify(authenticationManager,
//                Mockito.times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        Mockito.verify(userDetailsService,
//                Mockito.times(1)).loadUserByUsername("dzmitry@mail.ru");
//        Mockito.verify(provider,
//                Mockito.times(1)).createToken(userDetails);
//
//    }
//
//    @Test
//    public void authenticationFailTest() {
//        Mockito.when(userDetailsService.loadUserByUsername("fail@mail.ru"))
//                .thenThrow(new UsernameNotFoundException("User with email fail@mail.ru not found!"));
//
//        Exception exception = assertThrows(
//                BadCredentialsException.class,
//                () -> securityService.authentication("fail@mail.ru", "password"));
//        Assert.assertTrue(exception.getMessage().contains(
//                "Invalid Username or password!"
//        ));
//
//        Mockito.verify(authenticationManager,
//                Mockito.times(0)).authenticate(any(UsernamePasswordAuthenticationToken.class));
//        Mockito.verify(userDetailsService,
//                Mockito.times(1)).loadUserByUsername("fail@mail.ru");
//        Mockito.verify(provider,
//                Mockito.times(0)).createToken(any(UserDetailsImpl.class));
//    }
//
//    @Test
//    public void registration() {
//        User userTest = new User();
//        userTest.setId(1L);
//
//        Mockito.doReturn(userTest).when(userDAO).
//                save(any(User.class));
//
//        User user = securityService.registration(userTest);
//
//        Assert.assertNotNull(user);
//
//        Mockito.verify(userDAO,
//                Mockito.times(1)).save(userTest);
//        Mockito.verify(walletDAO,
//                Mockito.times(1)).save(any(Wallet.class));
//        Mockito.verify(bookmarkDAO,
//                Mockito.times(1)).save(any(Bookmark.class));
//    }
//
//    @Test
//    public void createModeratorUserTest() {
//        User userTest = new User();
//        userTest.setId(1L);
//
//        String password = "12345";
//
//        Mockito.doReturn(userTest).when(userDAO).
//                save(any(User.class));
//        Mockito.doReturn(password).when(customUtil)
//                .generatePassword(10);
//        Mockito.doReturn("awdad23f2223f")
//                .when(passwordEncoder).encode(password);
//
//        User user = securityService.createModeratorUser(userTest);
//
//        Assert.assertNotNull(user);
//
//        Mockito.verify(userDAO,
//                Mockito.times(1)).save(userTest);
//        Mockito.verify(walletDAO,
//                Mockito.times(1)).save(any(Wallet.class));
//        Mockito.verify(bookmarkDAO,
//                Mockito.times(1)).save(any(Bookmark.class));
//        Mockito.verify(passwordEncoder,
//                Mockito.times(1)).encode(password);
//        Mockito.verify(customUtil,
//                Mockito.times(1)).generatePassword(10);
//        Mockito.verify(mailService,
//                Mockito.times(1)).sendDataToEmail(any(MailDataDTO.class));
//    }
//}
