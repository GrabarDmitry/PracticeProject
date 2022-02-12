package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.Auto;
import com.auto.practiceproject.service.AutoService;
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
public class AutoServiceImpl implements AutoService {

    private final AutoDAO autoDAO;

    @Override
    public Auto findAutoById(Long id) {
        log.info("Service method called to find auto with id: {}", id);
        return autoDAO
                .findById(id)
                .orElseThrow(
                        () -> {
                            log.warn("Auto with Id: {} not found", id);
                            throw new ResourceException("Auto with Id: " + id + " not found");
                        });
    }

    @Override
    public Optional<Auto> findAuto(Long id) {
        log.trace("Service method called to view Auto with id: {}", id);
        return autoDAO.findById(id);
    }


}
