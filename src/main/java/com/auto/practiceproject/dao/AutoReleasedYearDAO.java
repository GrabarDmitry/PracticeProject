package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoReleasedYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoReleasedYearDAO extends JpaRepository<AutoReleasedYear, Long> {}
