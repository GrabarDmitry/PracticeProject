package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.User;
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
        locations = "classpath:application_dao_test.properties")
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp() {
        User userTest1 = new User();
        User userTest2 = new User();
        User userTest3 = new User();
        userTest1.setEmail("josh@mail.ru");
        userTest2.setEmail("nick@mail.ru");
        userTest3.setEmail("tyler@mail.ru");
        testEntityManager.persistAndFlush(userTest1);
        testEntityManager.persistAndFlush(userTest2);
        testEntityManager.persistAndFlush(userTest3);
    }

    @Test
    public void findUserByEmailTest() {
        Optional<User> user = userDAO.findUserByEmail("nick@mail.ru");

        Assert.assertTrue(user.isPresent());
        Assert.assertEquals(user.get().getEmail(), "nick@mail.ru");
    }

    @Test
    public void findUserByEmailIsNullTest() {
        Optional<User> user = userDAO.findUserByEmail("test@mail.ru");
        Assert.assertTrue(user.isEmpty());

    }

}
