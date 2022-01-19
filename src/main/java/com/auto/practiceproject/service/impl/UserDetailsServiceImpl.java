package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.UserDAO;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetailsImpl loadUserByUsername(String email) {
        log.info("Service method called to load User with email: {}", email);
        User user = userDAO.findUserByEmail(email).
                orElseThrow(() -> {
                    log.warn("User not found with email: {}", email);
                    throw new UsernameNotFoundException("User with email " + email + " not found!");
                });
        log.trace("Service method to load User with email: {}: ", email);
        return new UserDetailsImpl(user);
    }

}

