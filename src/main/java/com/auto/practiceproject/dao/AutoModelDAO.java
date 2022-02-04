package com.auto.practiceproject.dao;

import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.model.AutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutoModelDAO extends JpaRepository<AutoModel, Long>, JpaSpecificationExecutor<AutoModel> {

}
