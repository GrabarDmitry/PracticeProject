package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoReleasedYear;

import java.time.LocalDate;
import java.util.Optional;

public interface AutoReleasedYearService {
    AutoReleasedYear findAutoReleasedYearById(Long id);
}
