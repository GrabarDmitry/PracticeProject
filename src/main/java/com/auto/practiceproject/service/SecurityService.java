package com.auto.practiceproject.service;

import com.auto.practiceproject.model.User;

public interface SecurityService {

    String authentication(String email, String password);

    User registration(User user);

    User createModeratorUser(User user);

}
