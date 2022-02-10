package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutoBrandDAO extends JpaRepository<AutoBrand, Long> {}
