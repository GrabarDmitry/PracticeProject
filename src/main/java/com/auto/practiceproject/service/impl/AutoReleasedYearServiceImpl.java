package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.AutoReleasedYearDAO;
import com.auto.practiceproject.model.AutoReleasedYear;
import com.auto.practiceproject.service.AutoReleasedYearService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoReleasedYearServiceImpl implements AutoReleasedYearService {

    private final AutoReleasedYearDAO releasedYearDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<AutoReleasedYear> findAutoReleasedYearByReleased(LocalDate releasedYear) {
        log.trace("Service method called to view auto released year model with released : {}", releasedYear.getYear());
        return releasedYearDAO.findAutoReleasedYearByReleasedYear(releasedYear);
    }

}
