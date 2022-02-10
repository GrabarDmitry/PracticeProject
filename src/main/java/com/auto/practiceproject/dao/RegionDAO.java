package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegionDAO extends JpaRepository<Region, Long> {}
