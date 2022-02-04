package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoEngine;

import java.util.List;
import java.util.Optional;

public interface AutoEngineService {
    Optional<AutoEngine> findAutoEngine(Long id);

    AutoEngine findAutoEngineById(Long id);

    List<AutoEngine> findAllAutoEngine();
}