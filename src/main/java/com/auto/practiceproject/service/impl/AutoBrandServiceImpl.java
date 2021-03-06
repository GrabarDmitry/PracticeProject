package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoBrandDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoBrand;
import com.auto.practiceproject.service.AutoBrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class AutoBrandServiceImpl implements AutoBrandService {

  private final AutoBrandDAO autoBrandDAO;

  @Override
  public List<AutoBrand> findAll() {
    log.trace("Service method called to find all auto brand");
    return autoBrandDAO.findAll();
  }

  @Override
  public AutoBrand findAutoBrandById(Long id) {
    log.info("Service method called to find auto brand with id: {}", id);
    return autoBrandDAO
        .findById(id)
        .orElseThrow(
            () -> {
              log.warn("Auto brand with Id: {} not found", id);
              throw new ResourceException("Auto brand with Id: " + id + " not found");
            });
  }
}
