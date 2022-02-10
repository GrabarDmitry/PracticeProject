package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoEngine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutoEngineDAO extends JpaRepository<AutoEngine, Long> {}
