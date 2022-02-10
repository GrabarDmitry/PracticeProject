package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoModelDAO
    extends JpaRepository<AutoModel, Long>, JpaSpecificationExecutor<AutoModel> {}
