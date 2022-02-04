package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoTransmissionDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoTransmission;
import com.auto.practiceproject.service.AutoTransmissionService;
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
public class AutoTransmissionServiceImpl implements AutoTransmissionService {

    private final AutoTransmissionDAO autoTransmissionDAO;

    @Override
    public Optional<AutoTransmission> findAutoTransmission(Long id) {
        log.trace("Service method called to view Auto transmission with id: {}", id);
        return autoTransmissionDAO.findById(id);
    }

    @Override
    public List<AutoTransmission> findAllAutoTransmission() {
        log.trace("Service method called to find all auto transmission");
        return autoTransmissionDAO.findAll();
    }

    @Override
    public AutoTransmission findAutoTransmissionById(Long id) {
        log.info("Service method called to find auto transmission with id: {}", id);
        return autoTransmissionDAO.findById(id).
                orElseThrow(() -> {
                    log.warn("Auto transmission with Id: {} not found", id);
                    throw new ResourceException("Auto transmission with Id: " + id + " not found");
                });
    }

}
