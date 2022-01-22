package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoEngine;

import java.util.Optional;

public interface AutoEngineService {
    Optional<AutoEngine> findAutoEngineByType(String type);
}