package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Role;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class RoleDAOTest {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        testEntityManager.persistAndFlush(new Role("ADMIN"));
        testEntityManager.persistAndFlush(new Role("MODERATOR"));
        testEntityManager.persistAndFlush(new Role("USER"));
    }

    @Test
    public void findRoleByTileTest() {
        Optional<Role> role = roleDAO.findByTitle("USER");

        Assert.assertTrue(role.isPresent());
        Assert.assertEquals(role.get().getTitle(), "USER");
    }

    @Test
    public void findRoleByTitleIsNullTest() {
        Optional<Role> role = roleDAO.findByTitle("TEST");
        Assert.assertTrue(role.isEmpty());

    }


}
