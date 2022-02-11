package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoTransmissionDAO extends JpaRepository<AutoTransmission, Long> {}
