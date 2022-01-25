package com.auto.practiceproject.service;

import com.auto.practiceproject.model.User;

public interface WalletService {

    void payForServices(Double amount, User user);

}
