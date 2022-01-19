package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.RoleDAO;
import com.auto.practiceproject.model.Role;
import com.auto.practiceproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    @Override
    public Optional<Role> findRoleByTitle(String title) {
        log.trace("Service method called to view Role with title: {}", title);
        return roleDAO.findByTitle(title);
    }

}