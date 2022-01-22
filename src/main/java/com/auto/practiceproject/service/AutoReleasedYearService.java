package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoReleasedYear;

import java.time.LocalDate;
import java.util.Optional;

public interface AutoReleasedYearService {
    Optional<AutoReleasedYear> findAutoReleasedYearByReleased(LocalDate localDate);
}
