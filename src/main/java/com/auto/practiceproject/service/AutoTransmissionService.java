package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoTransmission;

import java.util.Optional;

public interface AutoTransmissionService {
    Optional<AutoTransmission> findAutoTransmissionByType(String type);
}