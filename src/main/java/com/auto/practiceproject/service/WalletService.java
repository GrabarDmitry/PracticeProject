package com.auto.practiceproject.service;

import com.auto.practiceproject.model.User;
import com.auto.practiceproject.model.Wallet;

import java.util.Optional;

public interface WalletService {

    void putMoney(Double amount, User user);

    void payForServices(Double amount, User user);

    Optional<Wallet> findWalletByUser(User user);

}
