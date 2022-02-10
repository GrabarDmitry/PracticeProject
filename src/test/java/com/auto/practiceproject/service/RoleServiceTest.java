//package com.auto.practiceproject.service;
//
//import com.auto.practiceproject.dao.RoleDAO;
//import com.auto.practiceproject.model.Role;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RoleServiceTest {
//
//    @Autowired
//    private RoleService roleService;
//
//    @MockBean
//    private RoleDAO roleDAO;
//
//    @Before
//    public void setUp() {
//        Role role = new Role("ADMIN");
//        role.setId(1l);
//
//        Mockito.when(roleDAO.findByTitle("ADMIN"))
//                .thenReturn(Optional.of(role));
//    }
//
//    @Test
//    public void findRoleTest() {
//        Optional<Role> role = roleService.findRoleByTitle("ADMIN");
//        Assert.assertTrue(role.isPresent());
//        Assert.assertEquals(role.get().getTitle(), "ADMIN");
//    }
//
//    @Test
//    public void findAutoBrandIsNullTest() {
//        Optional<Role> role = roleService.findRoleByTitle("USER");
//        Assert.assertTrue(role.isEmpty());
//    }
//
//}
