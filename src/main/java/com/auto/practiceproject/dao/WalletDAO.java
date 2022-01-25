package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.User;
import com.auto.practiceproject.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletDAO extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
