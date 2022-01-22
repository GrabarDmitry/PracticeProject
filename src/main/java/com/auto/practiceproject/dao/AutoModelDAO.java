package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoModel;
import com.auto.practiceproject.model.AutoReleasedYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoModelDAO extends JpaRepository<AutoModel, Long> {
    Optional<AutoModel> findAutoModelByTitleAndAutoBrandAndAutoReleasedYear(
            String title,
            AutoBrand brand,
            AutoReleasedYear autoReleasedYear
    );
}
