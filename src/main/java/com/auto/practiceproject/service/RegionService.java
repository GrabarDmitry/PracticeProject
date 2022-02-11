package com.auto.practiceproject.service;

import com.auto.practiceproject.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RegionService {
  Optional<Region> findRegion(Long id);

  Page<Region> findAllRegion(Pageable pageable);

  Region findRegionById(Long id);
}
