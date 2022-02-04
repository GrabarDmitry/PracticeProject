package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoModel;

import java.util.List;
import java.util.Optional;

public interface AutoModelService {

    public List<AutoModel> findAllAutoModel(String filter);

    Optional<AutoModel> findAutoModelByTitle(String title);

    Optional<AutoModel> findAutoModel(Long id);

    AutoModel findAutoModelById(Long id);
}
