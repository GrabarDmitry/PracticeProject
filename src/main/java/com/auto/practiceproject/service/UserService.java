package com.auto.practiceproject.service;

import com.auto.practiceproject.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByEmail(String email);

}
