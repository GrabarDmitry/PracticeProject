package com.auto.practiceproject.service.impl;

import com.auto.practiceproject.dao.RegionDAO;
import com.auto.practiceproject.model.Region;
import com.auto.practiceproject.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionDAO regionDAO;

    @Override
    @Transactional(readOnly = true, isolation = Isolation.SERIALIZABLE)
    public Optional<Region> findRegionByTitle(String title) {
        log.trace("Service method called to view Region with title: {}", title);
        return regionDAO.findRegionByTitle(title);
    }

}
