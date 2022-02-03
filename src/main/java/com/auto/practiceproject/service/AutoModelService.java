package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoModel;

import java.util.Optional;

public interface AutoModelService {
    Optional<AutoModel> findAutoModelByTitle(String title);
    Optional<AutoModel> findAutoModel(Long id);
}
