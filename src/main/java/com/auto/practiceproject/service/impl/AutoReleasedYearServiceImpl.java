package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoReleasedYearDAO;
import com.auto.practiceproject.exception.ResourceException;
import com.auto.practiceproject.model.AutoReleasedYear;
import com.auto.practiceproject.service.AutoReleasedYearService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
public class AutoReleasedYearServiceImpl implements AutoReleasedYearService {

  private final AutoReleasedYearDAO releasedYearDAO;

  @Override
  public AutoReleasedYear findAutoReleasedYearById(Long id) {
    log.info("Service method called to find auto released year with id: {}", id);
    return releasedYearDAO
        .findById(id)
        .orElseThrow(
            () -> {
              log.warn("Auto released year with Id: {} not found", id);
              throw new ResourceException("Auto released year with Id: " + id + " not found");
            });
  }
}
