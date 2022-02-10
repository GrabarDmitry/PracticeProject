package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.User;
import com.auto.practiceproject.model.Wallet;
import org.junit.Assert;
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
public class WalletDAOTest {

    @Autowired
    private WalletDAO walletDAO;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void findByUserTest() {
        User userTest1 = new User();
        userTest1.setEmail("alex@mail.ru");
        userTest1 = testEntityManager.persistAndFlush(userTest1);

        User userTest2 = new User();
        userTest2.setEmail("jon@mail.ru");
        userTest2 = testEntityManager.persistAndFlush(userTest2);

        testEntityManager.persistAndFlush(new Wallet(userTest1, 5D));
        testEntityManager.persistAndFlush(new Wallet(userTest2, 10D));

        Optional<Wallet> wallet = walletDAO.
                findByUser(userTest1);

        Assert.assertTrue(wallet.isPresent());
        Assert.assertEquals(wallet.get().getUser().getEmail(), "alex@mail.ru");
    }

    @Test
    public void findByUserIsNullTest() {
        User user = testEntityManager.persistAndFlush(new User());

        Optional<Wallet> wallet = walletDAO.
                findByUser(user);

        Assert.assertTrue(wallet.isEmpty());
    }

}
