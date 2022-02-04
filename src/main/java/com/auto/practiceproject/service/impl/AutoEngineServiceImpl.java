package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoEngineDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoEngine;
import com.auto.practiceproject.model.Region;
import com.auto.practiceproject.service.AutoEngineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class AutoEngineServiceImpl implements AutoEngineService {

    private final AutoEngineDAO autoEngineDAO;

    @Override
    public Optional<AutoEngine> findAutoEngine(Long id) {
        log.trace("Service method called to view auto engine with id: {}", id);
        return autoEngineDAO.findById(id);
    }

    @Override
    public List<AutoEngine> findAllAutoEngine() {
        log.trace("Service method called to find all auto engines");
        return autoEngineDAO.findAll();
    }

    @Override
    public AutoEngine findAutoEngineById(Long id) {
        log.info("Service method called to find autoEngine with id: {}", id);
        return autoEngineDAO.findById(id).
                orElseThrow(() -> {
                    log.warn("Auto engine with Id: {} not found", id);
                    throw new ResourceException("Auto engine with Id: " + id + " not found");
                });
    }

}
