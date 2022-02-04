package com.auto.practiceproject.service;

import com.auto.practiceproject.model.AutoBrand;

import java.util.List;
import java.util.Optional;

public interface AutoBrandService {
    List<AutoBrand> findAll();

    AutoBrand findAutoBrandById(Long id);
}
