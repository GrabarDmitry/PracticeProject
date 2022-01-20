package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.UserDAO;
import com.auto.practiceproject.model.User;
import com.auto.practiceproject.security.UserDetailsImpl;
import com.auto.practiceproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public Optional<User> findUserByEmail(String email) {
        log.trace("Service method called to view User with email: {}", email);
        return userDAO.findUserByEmail(email);
    }

    @Override
    public Optional<User> getCurrentUser() {
        log.info("Service method called to get Current User");
        return Optional.of(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(principal -> principal instanceof UserDetailsImpl)
                .map(principal -> Optional.of(((UserDetailsImpl) principal).getUser()))
                .orElse(Optional.empty());
    }

}


