package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoDAO extends JpaRepository<Auto, Long> {
}
