package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoTransmissionDAO;
import com.auto.practiceproject.model.AutoTransmission;
import com.auto.practiceproject.service.AutoTransmissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoTransmissionServiceImpl implements AutoTransmissionService {

    private final AutoTransmissionDAO autoTransmissionDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<AutoTransmission> findAutoTransmissionByType(String type) {
        log.trace("Service method called to view Auto transmission with type: {}", type);
        return autoTransmissionDAO.findAutoTransmissionByType(type);
    }

}
