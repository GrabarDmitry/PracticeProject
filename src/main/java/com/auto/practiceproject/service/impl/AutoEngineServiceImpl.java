package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoEngineDAO;
import com.auto.practiceproject.model.AutoEngine;
import com.auto.practiceproject.service.AutoEngineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class AutoEngineServiceImpl implements AutoEngineService {

    private final AutoEngineDAO autoEngineDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<AutoEngine> findAutoEngineByType(String type) {
        log.trace("Service method called to view auto engine with type: {}", type);
        return autoEngineDAO.findAutoEngineByType(type);
    }

    @Override
    public Optional<AutoEngine> findAutoEngine(Long id) {
        log.trace("Service method called to view auto engine with id: {}", id);
        return autoEngineDAO.findById(id);
    }

}
