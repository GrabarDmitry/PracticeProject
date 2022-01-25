package com.auto.practiceproject.service.impl;


import com.auto.practiceproject.dao.WalletDAO;
import com.auto.practiceproject.exception.WalletOperationException;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.model.Wallet;
import com.auto.practiceproject.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletDAO walletDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public void payForServices(Double amount, User user) {
        log.info("Service method called to pay for services by user with email: {}", user.getEmail());
        Wallet wallet = walletDAO.findByUser(user)
                .orElse(null);
        if (wallet.getBalance() < amount) {
            log.error("User with email {} don't have enough money to carry out the operation!", user.getEmail());
            throw new WalletOperationException("You don't have enough money to carry out the operation!");
        } else {
            wallet.setBalance(wallet.getBalance() - amount);
            walletDAO.saveAndFlush(wallet);
        }
    }

}
