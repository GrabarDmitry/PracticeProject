package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findRoleByTitle(String title);

}
