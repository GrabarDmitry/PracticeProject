package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.User;
import com.auto.practiceproject.model.Wallet;
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
public class WalletDAOTest {

    @Autowired
    private WalletDAO walletDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserDAO userDAO;

    @Before
    public void setUp() {
        User userTest1 = new User();
        userTest1.setEmail("alex@mail.ru");
        User userTest2 = new User();
        userTest2.setEmail("jon@mail.ru");
        testEntityManager.persistAndFlush(new Wallet(userTest1, 5D));
        testEntityManager.persistAndFlush(new Wallet(userTest2, 10D));
    }

    @Test
    public void findByUserTest() {
        Optional<User> user = userDAO.findUserByEmail("alex@mail.ru");

        Optional<Wallet> wallet = walletDAO.
                findByUser(user.get());

        Assert.assertTrue(wallet.isPresent());
        Assert.assertEquals(wallet.get().getUser().getEmail(), "alex@mail.ru");
    }

    @Test
    public void findByUserIsNullTest() {
        User user = new User();
        user.setId(50L);

        Optional<Wallet> wallet = walletDAO.
                findByUser(user);

        Assert.assertTrue(wallet.isEmpty());
    }

}
