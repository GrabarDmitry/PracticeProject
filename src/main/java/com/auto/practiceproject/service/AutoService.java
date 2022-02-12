package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Auto;

import java.util.Optional;

public interface AutoService {
  Auto findAutoById(Long id);

  Optional<Auto> findAuto(Long id);
}
