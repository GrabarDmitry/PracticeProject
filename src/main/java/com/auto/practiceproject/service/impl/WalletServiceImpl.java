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

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

  private final WalletDAO walletDAO;

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void putMoney(Double amount, User user) {
    log.info("Service method called to pup money to wallet, user email: {}", user.getEmail());
    Wallet wallet = walletDAO.findByUser(user).orElse(null);
    wallet.setBalance(wallet.getBalance() + amount);
    walletDAO.saveAndFlush(wallet);
  }

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public void payForServices(Double amount, User user) {
    log.info("Service method called to pay for services by user with email: {}", user.getEmail());
    Wallet wallet = walletDAO.findByUser(user).orElse(null);
    if (wallet.getBalance() < amount) {
      log.error(
          "User with email {} don't have enough money to carry out the operation!",
          user.getEmail());
      throw new WalletOperationException("You don't have enough money to carry out the operation!");
    } else {
      wallet.setBalance(wallet.getBalance() - amount);
      walletDAO.saveAndFlush(wallet);
    }
  }

  @Override
  @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
  public Optional<Wallet> findWalletByUser(User user) {
    log.trace("Service method called to view Wallet by user, user id : {}", user.getId());
    return walletDAO.findByUser(user);
  }
}
