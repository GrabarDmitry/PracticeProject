package com.auto.practiceproject.service;

import com.auto.practiceproject.dao.WalletDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.exception.WalletOperationException;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.model.Wallet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceTest {

    @Autowired
    private WalletService walletService;

    @MockBean
    private WalletDAO walletDAO;

    @Before
    public void setUp() {
        User user = new User();
        user.setId(1L);
        Wallet wallet = new Wallet(user, 10D);

        Mockito.when(walletDAO.findByUser(user))
                .thenReturn(Optional.of(wallet));
    }

    @Test
    public void putMoneyTest() {
        User user = new User();
        user.setId(1L);
        Wallet walletTest = new Wallet();
        walletTest.setBalance(10D);

        Mockito.when(walletDAO.findByUser(user)).
                thenReturn(Optional.of(walletTest));

        walletService.putMoney(5D, user);

        Mockito.verify(walletDAO,
                Mockito.times(1)).saveAndFlush(walletTest);
    }

    @Test
    public void findWalletByUserTest() {
        User user = new User();
        user.setId(1L);

        Optional<Wallet> optionalWallet = walletService.findWalletByUser(user);

        Assert.assertTrue(optionalWallet.isPresent());
        Assert.assertEquals(optionalWallet.get().getUser(), user);
    }

    @Test
    public void findWalletByUserIsNullTest() {
        User user = new User();
        user.setId(2L);

        Optional<Wallet> optionalWallet = walletService.findWalletByUser(user);

        Assert.assertTrue(optionalWallet.isEmpty());
    }

    @Test
    public void payForServicesTest() {
        User user = new User();
        user.setId(1L);

        walletService.payForServices(5D, user);

        Mockito.verify(walletDAO,
                Mockito.times(1)).findByUser(user);
        Mockito.verify(walletDAO,
                Mockito.times(1)).saveAndFlush(any(Wallet.class));
    }

    @Test
    public void payForServicesFailTest() {
        User user = new User();
        user.setId(1L);

        Exception exception = assertThrows(
                WalletOperationException.class,
                () -> walletService.payForServices(20D, user));
        Assert.assertTrue(exception.getMessage().contains(
                "You don't have enough money to carry out the operation!"
        ));

        Mockito.verify(walletDAO,
                Mockito.times(1)).findByUser(user);
        Mockito.verify(walletDAO,
                Mockito.times(0)).saveAndFlush(any(Wallet.class));
    }
}
