package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoEngine;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.model.AutoReleasedYear;

import java.util.Optional;

public interface AutoModelService {

    Optional<AutoModel> findAutoModelByTitle(String title);

    Optional<AutoModel> findAutoModelByTitleAndAutoBrandAndAutoReleasedYear(
            String title,
            AutoBrand brand,
            AutoReleasedYear autoReleasedYear
    );
}
