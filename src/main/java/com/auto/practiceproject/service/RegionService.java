package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Region;

import java.util.Optional;

public interface RegionService {
    Optional<Region> findRegion(Long id);

    Optional<Region> findRegionByTitle(String title);
}
