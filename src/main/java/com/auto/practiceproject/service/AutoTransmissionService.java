package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoTransmission;

import java.util.List;
import java.util.Optional;

public interface AutoTransmissionService {
    Optional<AutoTransmission> findAutoTransmissionByType(String type);

    Optional<AutoTransmission> findAutoTransmission(Long id);

    List<AutoTransmission> findAllAutoTransmission();

    AutoTransmission findAutoTransmissionById(Long id);
}
